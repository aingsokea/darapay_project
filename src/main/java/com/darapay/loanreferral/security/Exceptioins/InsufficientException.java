package com.darapay.loanreferral.security.Exceptioins;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INSUFFICIENT_STORAGE)
public class InsufficientException extends RuntimeException {
    public InsufficientException(String exception) {
        super(exception);
    }
}
