package com.darapay.loanreferral.security.Exceptioins;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecoredNotFoundException extends RuntimeException {
    public RecoredNotFoundException(String exception) {
        super(exception);
    }
}
