package com.dahhong.whoami.global.exception.customException;

import org.springframework.security.core.AuthenticationException;

public class AuthorizationFailureException extends AuthenticationException {

    public AuthorizationFailureException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
