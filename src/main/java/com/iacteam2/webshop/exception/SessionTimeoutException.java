package com.iacteam2.webshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT)
public class SessionTimeoutException extends RuntimeException {
    public SessionTimeoutException(String message) {
        super(message);
    }
}
