package com.codewiz.problemSubmission.judge.services

import com.codewiz.problemSubmission.commons.dto.SubmissionQueueDto
import com.codewiz.problemSubmission.judge.persistance.model.*
import com.codewiz.problemSubmission.judge.persistance.repository.SubmissionRepository
import com.codewiz.problemSubmission.judge.persistance.repository.TestResultRepository
import com.codewiz.problemSubmission.judge.utils.runCommand
import com.codewiz.problemSubmission.judge.utils.runCommandWithInput
import com.google.gson.Gson
import org.apache.commons.io.FileUtils
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service
import java.io.File

@Service
@RabbitListener(queues = ["submission_queue"])
class JudgeService(
    private val _submissionRepository: SubmissionRepository,
    private val _testResultRepository: TestResultRepository
) {
    private val workDir = "pwd".runCommand("./").trim() + "/workdir"

    private val fileName = "source"
    private val compiledSource = "compiled"

    private val compileTimeout: Long = 10 //s
    private val runTimeout: Long = 15 //s

    private val runnerPath = "pwd".runCommand("./").trim() + "/runner"

    //to convert to/from json
    private val gson = Gson()

    private data class Language(
        val sourceFile: String,
        val compile: String?,
        val execute: String
    )

    private val langMap = mapOf(
        "C" to Language(
            sourceFile = "${workDir}/${fileName}.c",
            compile = "gcc ${workDir}/${fileName}.c -w -O2 -fomit-frame-pointer -o ${workDir}/${compiledSource}",
            execute = "${workDir}/${compiledSource}"
        ),
        "Cpp" to Language(
            sourceFile = "${workDir}/${fileName}.cpp",
            compile = "g++ ${workDir}/${fileName}.cpp -w -O2 -fomit-frame-pointer -o ${workDir}/${compiledSource}",
            execute = "${workDir}/${compiledSource}"
        ),
        "Java" to Language(
            sourceFile = "${workDir}/${fileName}.java",
            compile = "javac -g:none -Xlint -d $workDir ${workDir}/${fileName}.java",
            execute = "java -classpath $workDir Main"
        )
    )
    @RabbitHandler
    fun receive(submissionJson: String) {

        val submission = gson.fromJson(submissionJson, SubmissionQueueDto::class.java)

        //clearing dir first
        val wd = File("$workDir/")
        if(!wd.exists())
            wd.mkdir()

        FileUtils.cleanDirectory(wd);
        //log for wrong language and abort
        langMap[submission.usedLanguage] ?: throw RuntimeException("Language not supported")

        //write code to file
        File(langMap[submission.usedLanguage]!!.sourceFile).writeText(submission.submissionText)

        val submissionEntity = _submissionRepository.findById(submission.submissionID)
            .orElseThrow { RuntimeException("Wrong submission, cant find in database") }

        langMap[submission.usedLanguage]!!.compile?.let {
            val compileOutput = it.runCommand(workDir, compileTimeout)

            println(compileOutput)
            if (compileOutput == null) {
                //status Compile_Time_Exceeded
                submissionEntity.status = SubmissionStatus.Compile_Time_Exceeded
                _submissionRepository.save(submissionEntity)
                return
            }

            if (compileOutput.isNotBlank()) {
                //status Compile_Error
                submissionEntity.status = SubmissionStatus.Compile_Error
                _submissionRepository.save(submissionEntity)
                return
            }
        }

        submission.testList.map {
            val metricsOutput =
                ("${runnerPath}/runner " + langMap[submission.usedLanguage]!!.execute).runCommandWithInput(it.testIn, workDir, runTimeout)
            //read output
            val runnerOutput = File("${workDir}/runner.out").readText(Charsets.US_ASCII)

            //check file output for segfault
            if ("Segmentation fault" in runnerOutput) {
                //out of machine memory
                _testResultRepository.save(
                    TestResult(
                        TestResultKey(
                            submissionID = submission.submissionID,
                            testID = it.testID
                        ),
                        null,
                        null,
                        TestStatus.Memory_Overflow,
                        submissionEntity
                    )
                )
                return
            }
            //check output for timeout
            if (metricsOutput == null) {
                //Out of time
                _testResultRepository.save(
                    TestResult(
                        TestResultKey(
                            submissionID = submission.submissionID,
                            testID = it.testID
                        ),
                        null,
                        null,
                        TestStatus.Run_Time_Exceeded,
                        submissionEntity
                    )
                )
                return
            }
            //check for matching
            if (it.testOut == runnerOutput) {
                //amazing
                val metrics = metricsOutput.split(",")
                val memory = metrics[0].trim().toInt()
                val time = metrics[1].trim().toInt()

                _testResultRepository.save(
                    TestResult(
                        TestResultKey(
                            submissionID = submission.submissionID,
                            testID = it.testID
                        ),
                        time,
                        memory,
                        TestStatus.Succeded,
                        submissionEntity
                    )
                )
            } else {
                //non matching outputs
                _testResultRepository.save(
                    TestResult(
                        TestResultKey(
                            submissionID = submission.submissionID,
                            testID = it.testID
                        ),
                        null,
                        null,
                        TestStatus.Non_Matching_Output,
                        submissionEntity
                    )
                )
            }
        }
        //change submission status as processed in db
        submissionEntity.status = SubmissionStatus.Processed
        _submissionRepository.save(submissionEntity)
    }
}