package com.codewiz.problemSubmission.submissionController

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan(basePackages = ["com.codewiz.problemSubmission.commons.persistance.model"])
class SubmissionControllerApplication

fun main(args: Array<String>) {
	runApplication<SubmissionControllerApplication>(*args)
}
