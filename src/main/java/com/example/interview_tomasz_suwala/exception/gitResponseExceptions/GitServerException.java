package com.example.interview_tomasz_suwala.exception.gitResponseExceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GitServerException extends Exception {

    private int statusCode;
    private String reason;

    @Override
    public String getMessage() {
        return "ERROR_" + statusCode + "_REASON_" + reason;
    }
}
