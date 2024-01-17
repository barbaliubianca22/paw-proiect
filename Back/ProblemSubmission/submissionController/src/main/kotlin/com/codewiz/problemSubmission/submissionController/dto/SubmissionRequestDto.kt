package com.codewiz.problemSubmission.submissionController.dto

import com.codewiz.problemSubmission.commons.dto.TestUnit
import java.io.Serializable

data class SubmissionRequestDto(
    val userID: Long,
    val problemID: Long,
    val submissionText: String,
    val usedLanguage: String,
    val testList: List<TestUnit>
): Serializable