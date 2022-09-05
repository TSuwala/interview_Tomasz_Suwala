package com.example.interview_tomasz_suwala.controller;

import com.example.interview_tomasz_suwala.model.GitHubRepositoryDetails;
import com.example.interview_tomasz_suwala.service.GitHubRepositoryDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GitHubRepositoryDetailsController {

    private final GitHubRepositoryDetailsService gitHubRepositoryDetailsService;

    @GetMapping("/repositories/{owner}/{repositoryName}")
    public ResponseEntity<GitHubRepositoryDetails> getDetails(@PathVariable String owner, @PathVariable String repositoryName) {
        return ResponseEntity.ok(gitHubRepositoryDetailsService.getDetails(owner, repositoryName));
    }
}