package com.codewiz.problemSubmission.submissionController.persistance.repository

import com.codewiz.problemSubmission.commons.persistance.model.Submission
import org.springframework.data.jpa.repository.JpaRepository

interface SubmissionRepository : JpaRepository<Submission, Long> {

}