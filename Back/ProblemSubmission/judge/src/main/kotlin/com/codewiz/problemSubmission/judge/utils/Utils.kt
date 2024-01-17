package com.codewiz.problemSubmission.judge.utils

import java.io.File
import java.util.concurrent.TimeUnit

fun String.runCommand(workingDir: String, timeoutSec: Long): String? {
    val parts = this.split("\\s".toRegex())
    val proc = ProcessBuilder(*parts.toTypedArray())
        .directory(File(workingDir))
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)
        .redirectErrorStream(true)
        .start()

    if (!proc.waitFor(timeoutSec, TimeUnit.SECONDS))
    {
        proc.destroyForcibly()
        return null
    }
    println(proc.errorStream.bufferedReader().readText())
    return proc.inputStream.bufferedReader().readText()
}

fun String.runCommand(workingDir: String): String {
    val parts = this.split("\\s".toRegex())
    val proc = ProcessBuilder(*parts.toTypedArray())
        .directory(File(workingDir))
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)
        .redirectErrorStream(true)
        .start()

    proc.waitFor()

    println(proc.errorStream.bufferedReader().readText())
    return proc.inputStream.bufferedReader().readText()
}

fun String.runCommandWithInput(input:String, workingDir: String, timeoutSec: Long): String? {
    val parts = this.split("\\s".toRegex())
    val proc = ProcessBuilder(*parts.toTypedArray())
        .directory(File(workingDir))
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)
        .redirectErrorStream(true)
        .start()

    proc.outputStream.write(input.toByteArray())
    proc.outputStream.flush()
    proc.outputStream.close()

    if (!proc.waitFor(timeoutSec, TimeUnit.SECONDS))
    {
        proc.destroyForcibly()
        return null
    }
    return proc.inputStream.bufferedReader().readText()
}