package com.wayn.core.exception;

import org.springframework.security.core.AuthenticationException;

public class ValidataCodeException extends AuthenticationException {

    public ValidataCodeException(String msg) {
        super(msg);
    }
}
