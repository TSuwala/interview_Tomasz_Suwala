package com.example.interview_tomasz_suwala.exception.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponseDto {
    String message;

    public static ErrorResponseDto fromException(Exception e) {
        return ErrorResponseDto.builder()
                .message(e.getMessage())
                .build();
    }
}
