package com.codewiz.problemSubmission.commons.dto

import java.io.Serializable

data class SubmissionQueueDto (
    val submissionID : Long,
    val submissionText: String,
    val usedLanguage: String,
    val testList: List<TestUnit>
): Serializable

data class TestUnit(
    val testID: Long,
    val testIn: String,
    val testOut: String
): Serializable