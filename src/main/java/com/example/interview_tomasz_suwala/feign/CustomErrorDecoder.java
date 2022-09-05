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
        if (response.status() == 404) {
            return new GitClientNotFoundException();
        } else if (response.status() == 400) {
            return new GitClientBadRequestException();
        } else if (response.status() >= 400 && response.status() <= 499) {
            return new GitClientException(response.status(), response.reason());
        }

        if (response.status() >= 500 && response.status() <= 599) {
            return new GitServerException(
                    response.status(),
                    response.reason()
            );
        }
        return errorStatus(methodKey, response);
    }
}
