package com.example.interview_tomasz_suwala.exception;

import com.example.interview_tomasz_suwala.exception.dto.ErrorResponseDto;
import com.example.interview_tomasz_suwala.exception.gitResponseExceptions.GitClientBadRequestException;
import com.example.interview_tomasz_suwala.exception.gitResponseExceptions.GitClientException;
import com.example.interview_tomasz_suwala.exception.gitResponseExceptions.GitClientNotFoundException;
import com.example.interview_tomasz_suwala.exception.gitResponseExceptions.GitServerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(GitClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleGitNotFoundException(GitClientNotFoundException e) {
        return ErrorResponseDto.fromException(e);
    }

    @ExceptionHandler(GitClientBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleGitNotFoundException(GitClientBadRequestException e) {
        return ErrorResponseDto.fromException(e);
    }

    @ExceptionHandler(GitClientException.class)
    public ErrorResponseDto handleGitClientException(GitClientException e) {
        return ErrorResponseDto.fromException(e);
    }

    @ExceptionHandler(GitServerException.class)
    public ErrorResponseDto handleGitServerException(GitServerException e) {
        return ErrorResponseDto.fromException(e);
    }
}
