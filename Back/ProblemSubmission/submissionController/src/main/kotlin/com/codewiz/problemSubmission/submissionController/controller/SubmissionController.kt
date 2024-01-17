package com.codewiz.problemSubmission.submissionController.controller

import com.codewiz.problemSubmission.commons.dto.SubmissionQueueDto
import com.codewiz.problemSubmission.commons.persistance.model.Submission
import com.codewiz.problemSubmission.commons.persistance.model.SubmissionStatus
import com.codewiz.problemSubmission.submissionController.persistance.repository.SubmissionRepository
import com.codewiz.problemSubmission.submissionController.dto.SubmissionRequestDto
import com.google.gson.Gson
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*


@RestController
@RequestMapping("")
class SubmissionController(
    private val _submissionRepository: SubmissionRepository,
    private val _rabbitTemplate: RabbitTemplate
) {
    //to convert to/from json
    val gson = Gson()

    @PostMapping("/")
    fun acceptSubmission(
        @RequestBody submission: SubmissionRequestDto
    ): ResponseEntity<Any> {

        var newSubmission = Submission(
            submissionID = 0,
            userID = submission.userID,
            submissionText = submission.submissionText,
            time = Date(),
            testResults = emptySet(),
            problemID = submission.problemID,
            status = SubmissionStatus.Waiting,
            usedLanguage = submission.usedLanguage
        )
        newSubmission = _submissionRepository.save(newSubmission)

        val submissionJson = gson.toJson(SubmissionQueueDto(
            submissionID = newSubmission.submissionID,
            submissionText = newSubmission.submissionText,
            usedLanguage = newSubmission.usedLanguage,
            testList = submission.testList
        ))
        //submit work to a worker with id, text and testUnit vector
        _rabbitTemplate.convertSendAndReceive(
            "submission_queue", submissionJson
        )

        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(newSubmission.submissionID).toUri()
        ).build()
    }

    //list submissions of the user

    //get one submission
    @GetMapping("/{id}")
    fun getSubmission(
        @PathVariable id: Long
    ): ResponseEntity<Any> {

        return ResponseEntity.ok(
            _submissionRepository.findById(id)
        )
    }

    @GetMapping("/")
    fun getSubmissions(
    ): ResponseEntity<Any> {

        return ResponseEntity.ok(
            object {
                val a = "#include <stdio.h>\n" +
                        "\n" +
                        "int main(){\n" +
                        "    int a;\n" +
                        "    scanf(\"%d\", &a);\n" +
                        "    printf(\"%d\", a);\n" +
                        "\n" +
                        "    return 0;\n" +
                        "}\n"
            }

        )
    }


}