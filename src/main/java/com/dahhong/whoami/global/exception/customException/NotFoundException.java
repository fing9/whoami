package com.dahhong.whoami.global.exception.customException;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

}
