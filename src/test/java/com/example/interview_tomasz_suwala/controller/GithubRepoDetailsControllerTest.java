package com.example.interview_tomasz_suwala.controller;

import com.example.interview_tomasz_suwala.feign.FeignService;
import com.example.interview_tomasz_suwala.model.GitHubRepositoryDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GithubRepoDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FeignService feignService;


    @Test
    void shouldReturnFormattedGithubRepositoryDetails() throws Exception {
        Mockito.when(feignService.getGithubRepo("tester", "testRepository"))
                .thenReturn("{\n" +
                        "    \"full_name\": \"tester/testRepository\",\n" +
                        "    \"description\": \"Repository prepared for test\",\n" +
                        "    \"clone_url\": \"https://github.com/tester/testerRepository.git\",\n" +
                        "    \"stargazers_count\": \"125\",\n" +
                        "    \"created_at\": \"2021-08-25T14:51:49Z\"\n" +
                        "}");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/repositories/tester/testRepository")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        GitHubRepositoryDetails response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), GitHubRepositoryDetails.class);

        assertNotNull(response);
        assertNotNull(response.getFullName());
        assertNotNull(response.getDescription());
        assertNotNull(response.getCloneUrl());
        assertNotNull(response.getStars());
        assertNotNull(response.getCreatedAt());

        assertEquals("tester/testRepository", response.getFullName());
        assertEquals("Repository prepared for test", response.getDescription());
        assertEquals("https://github.com/tester/testerRepository.git", response.getCloneUrl());
        assertEquals("125", response.getStars());
        assertEquals((Instant.parse("2021-08-25T14:51:49Z")
                .atZone(ZoneId
                        .of(Calendar
                                .getInstance()
                                .getTimeZone()
                                .getID()))
                .format(DateTimeFormatter
                        .ofLocalizedDateTime(FormatStyle.SHORT)
                        .withLocale(Locale.getDefault())
                )), response.getCreatedAt());
    }


}