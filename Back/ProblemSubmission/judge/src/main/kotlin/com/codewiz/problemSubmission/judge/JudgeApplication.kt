package com.codewiz.problemSubmission.judge

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan(basePackages = ["com.codewiz.problemSubmission.judge.persistance.model"])
class JudgeApplication

fun main(args: Array<String>) {
	runApplication<JudgeApplication>(*args)
}
