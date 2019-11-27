package org.lock14.angularwebapp.resource;

import org.lock14.angularwebapp.api.ApiError;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ApiError handleHttpClientError(HttpRequest request,
                                          HttpClientErrorException ex) {
        return getError(request, ex.getStatusCode(), ex.getResponseBodyAsString());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ApiError handleHttpServerError(HttpRequest request,
                                          HttpServerErrorException ex) {
        return getError(request, ex.getStatusCode(), ex.getResponseBodyAsString());
    }

    private ApiError getError(HttpRequest request, HttpStatus status, String message) {
        return ApiError.withTimeStamp(LocalDateTime.now())
                       .withStatus(status.value())
                       .withError(status.getReasonPhrase())
                       .withMessage(message)
                       .withPath(request.getURI().getPath())
                       .build();
    }
}
