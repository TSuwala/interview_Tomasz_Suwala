package com.example.interview_tomasz_suwala.feign;

import com.example.interview_tomasz_suwala.exception.gitResponseExceptions.GitClientBadRequestException;
import com.example.interview_tomasz_suwala.exception.gitResponseExceptions.GitClientException;
import com.example.interview_tomasz_suwala.exception.gitResponseExceptions.GitClientNotFoundException;
import com.example.interview_tomasz_suwala.exception.gitResponseExceptions.GitServerException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Service;

import static feign.FeignException.errorStatus;

@Service
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()) {
            case 404:
                return new GitClientNotFoundException();
            case 400:
                return new GitClientBadRequestException();
            case 401:
            case 499:
                return new GitClientException(response.status(), response.reason());
            case 500:
            case 599:
                return new GitServerException(response.status(), response.reason());
            default:
                return errorStatus(methodKey, response);

        }
    }
}
