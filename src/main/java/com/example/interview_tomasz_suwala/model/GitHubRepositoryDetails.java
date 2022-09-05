package com.example.interview_tomasz_suwala.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GitHubRepositoryDetails {

    @JsonAlias({"fullName", "full_name"})
    private String fullName;

    private String description;

    @JsonAlias({"cloneUrl", "clone_url"})
    private String cloneUrl;

    @JsonAlias({"stars", "stargazers_count"})
    private String stars;

    @JsonAlias({"createdAt", "created_at"})
    private String createdAt;

}
