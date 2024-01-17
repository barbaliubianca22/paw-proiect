package com.example.problemservice.model;

import jakarta.persistence.*;

@Entity
public class Problem {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long problemId;

   @Column(nullable = false)
    private String problemTitle;

   @Column(nullable = false)
    private String problemDescription;

   @Column(nullable = false)
    private int difficultyLevel;

    public Problem(Long problemId, String problemTitle, String problemDescription, int difficultyLevel) {
        this.problemId = problemId;
        this.problemTitle = problemTitle;
        this.problemDescription = problemDescription;
        this.difficultyLevel = difficultyLevel;
    }
    public Problem(){}

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public String getProblemTitle() {
        return problemTitle;
    }

    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    // constructori, getteri, setteri, etc.
}
