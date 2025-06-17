package com.rohan.rlimiter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "Word not found in dictionary")
public class ApplicationNotFound extends RuntimeException {
    public ApplicationNotFound(String message) {
        super(message);
    }
}
