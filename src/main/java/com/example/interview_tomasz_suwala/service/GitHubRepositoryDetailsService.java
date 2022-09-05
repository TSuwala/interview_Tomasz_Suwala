package com.example.interview_tomasz_suwala.service;

import com.example.interview_tomasz_suwala.feign.FeignService;
import com.example.interview_tomasz_suwala.model.GitHubRepositoryDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class GitHubRepositoryDetailsService {

    private final FeignService feignService;

    private final ObjectMapper objectMapper;

    @SneakyThrows
    public GitHubRepositoryDetails getDetails(String owner, String repositoryName) {
        GitHubRepositoryDetails details = objectMapper.readValue(feignService.getGithubRepo(owner, repositoryName), GitHubRepositoryDetails.class);
        setCurrentTimeZone(details);
        return details;
    }

    private void setCurrentTimeZone(GitHubRepositoryDetails details) {
        details.setCreatedAt(Instant.parse(details.getCreatedAt())
                .atZone(ZoneId
                        .of(Calendar
                                .getInstance()
                                .getTimeZone()
                                .getID()))
                .format(DateTimeFormatter
                        .ofLocalizedDateTime(FormatStyle.SHORT)
                        .withLocale(Locale.getDefault())
                ));
    }

}
