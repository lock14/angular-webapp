package org.lock14.angularwebapp.resource;

import org.lock14.angularwebapp.api.Error;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Error> handleHttpClientError(HttpRequest request,
                                                       HttpClientErrorException ex) {
        return errorResponse(request, ex.getStatusCode(), ex.getResponseBodyAsString());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<Error> handleHttpServerError(HttpRequest request,
                                                       HttpServerErrorException ex) {
        return errorResponse(request, ex.getStatusCode(), ex.getResponseBodyAsString());
    }

    private ResponseEntity<Error> errorResponse(HttpRequest request, HttpStatus status, String message) {
        return ResponseEntity.status(status)
                             .body(Error.withTimeStamp(LocalDateTime.now())
                                        .withStatus(status.value())
                                        .withError(status.getReasonPhrase())
                                        .withMessage(message)
                                        .withPath(request.getURI().getPath())
                                        .build());
    }
}
