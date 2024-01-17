package com.codewiz.problemSubmission.judge.persistance.repository

import com.codewiz.problemSubmission.judge.persistance.model.Submission
import com.codewiz.problemSubmission.judge.persistance.model.TestResult
import org.springframework.data.jpa.repository.JpaRepository

interface SubmissionRepository : JpaRepository<Submission, Long> {

}

interface TestResultRepository : JpaRepository<TestResult, Long> {

}