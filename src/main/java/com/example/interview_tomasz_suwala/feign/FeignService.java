package com.example.interview_tomasz_suwala.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "github-client", url = "https://api.github.com/", configuration = FeignClientConfiguration.class)
public interface FeignService {

    @GetMapping(value = "/repos/{owner}/{repositoryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    String getGithubRepo(@PathVariable String owner, @PathVariable String repositoryName);
}
