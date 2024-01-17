package com.codewiz.problemSubmission.judge.persistance.model

import jakarta.persistence.*


enum class TestStatus{
    Succeded,
    Memory_Overflow,
    Run_Time_Exceeded,
    Non_Matching_Output
}

@Embeddable
class TestResultKey(
    @Column(name = "submissionID")
    var submissionID : Long,
    @Column(name = "testID")
    var testID: Long
)

@Entity
@Table(name = "TestResult")
class TestResult(
    @EmbeddedId
    var id: TestResultKey,
    var evaluationTimeMsec: Int?,
    var memUsedKbytes: Int?,
    @Enumerated(EnumType.STRING)
    var status: TestStatus,

    @ManyToOne
    @MapsId("submissionID")
    @JoinColumn(name = "submissionID")
    val submission: Submission
)