package org.lock14.angularwebapp.resource;

import org.lock14.angularwebapp.domain.ErrorResponse;
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
    public ErrorResponse handleHttpClientError(HttpRequest request,
                                               HttpClientErrorException ex) {
        return getError(request, ex.getStatusCode(), ex.getResponseBodyAsString());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ErrorResponse handleHttpServerError(HttpRequest request,
                                               HttpServerErrorException ex) {
        return getError(request, ex.getStatusCode(), ex.getResponseBodyAsString());
    }

    private ErrorResponse getError(HttpRequest request, HttpStatus status, String message) {
        return ErrorResponse.withTimeStamp(LocalDateTime.now())
                            .withStatus(status.value())
                            .withError(status.getReasonPhrase())
                            .withMessage(message)
                            .withPath(request.getURI().getPath())
                            .build();
    }

}
