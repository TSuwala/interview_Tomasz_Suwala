package com.example.interview_tomasz_suwala.exception.gitResponseExceptions;

public class GitClientNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return "REPOSITORY_OR_OWNER_NOT_FOUND";
    }

}
