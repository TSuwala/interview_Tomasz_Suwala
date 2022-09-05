package com.example.interview_tomasz_suwala.exception.gitResponseExceptions;

public class GitClientBadRequestException extends Exception {

    @Override
    public String getMessage() {
        return "BAD_REQUEST";
    }

}
