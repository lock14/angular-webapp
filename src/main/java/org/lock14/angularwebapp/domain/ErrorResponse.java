package org.lock14.angularwebapp.domain;

import java.time.LocalDateTime;

public final class ErrorResponse {
    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;

    private ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public static ErrorResponseBuilder withTimeStamp(LocalDateTime timeStamp) {
        return new ErrorResponseBuilder().withTimestamp(timeStamp);
    }

    public static ErrorResponseBuilder withStatus(int status) {
        return new ErrorResponseBuilder().withStatus(status);
    }

    public static ErrorResponseBuilder withError(String error) {
        return new ErrorResponseBuilder().withError(error);
    }

    public static ErrorResponseBuilder withMessage(String message) {
        return new ErrorResponseBuilder().withMessage(message);
    }

    public static ErrorResponseBuilder withPath(String path) {
        return new ErrorResponseBuilder().withPath(path);
    }

    public static final class ErrorResponseBuilder {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String path;

        private ErrorResponseBuilder() {
        }

        public ErrorResponseBuilder withTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ErrorResponseBuilder withStatus(int status) {
            this.status = status;
            return this;
        }

        public ErrorResponseBuilder withError(String error) {
            this.error = error;
            return this;
        }

        public ErrorResponseBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponseBuilder withPath(String path) {
            this.path = path;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(timestamp, status, error, message, path);
        }
    }
}
