package com.example.problemservice.services;

import com.example.problemservice.ProblemRepository;
import com.example.problemservice.model.Problem;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/problems")
public class ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    @GetMapping
    public List<Problem> getAllProblems() {
        return (List<Problem>) problemRepository.findAll();
    }

    @GetMapping("/{id}")
    public Problem getProblemById(@PathVariable Long id) {
        return problemRepository.findById(String.valueOf(id)).orElse(null);
    }

    @PostMapping("/add")
    public Problem addProblem(@RequestBody JsonNode problemData) {
        String problemTitle = problemData.findValue("problemTitle").asText();
        String problemDescription = problemData.findValue("problemDescription").asText();
        Integer difficultyLevel = problemData.findValue("difficultyLevel").asInt();

        Problem problem = new Problem();
        problem.setProblemTitle(problemTitle);
        problem.setProblemDescription(problemDescription);
        problem.setDifficultyLevel(difficultyLevel);
        return problemRepository.save(problem);
    }

    @DeleteMapping("/{id}")
    public void deleteProblem(@PathVariable Long id) {
        problemRepository.deleteById(String.valueOf(id));
    }
}