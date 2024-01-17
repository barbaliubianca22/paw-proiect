package com.codewiz.problemSubmission.judge.persistance.model

import jakarta.persistence.*
import java.util.Date

enum class SubmissionStatus{
    Waiting,
    Compile_Error,
    Compile_Time_Exceeded,
    Processed
}

@Entity
@Table(name = "Submission")
class Submission (
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id var submissionID : Long?,
    var userID: Long,
    var problemID: Long,
    var submissionText: String,
    var usedLanguage: String,
    var time: Date,
    @Enumerated(EnumType.STRING)
    var status: SubmissionStatus,

    @OneToMany(mappedBy="submission")
    val testResults: Set<TestResult>
)