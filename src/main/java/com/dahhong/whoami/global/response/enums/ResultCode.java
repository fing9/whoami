package com.dahhong.whoami.global.response.enums;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS("요청을 성공적으로 처리했습니다.", "200"),

    // common
    BAD_REQUEST("요청에 오류가 있습니다.", "400"),
    FORBIDDEN("허용되지 않은 접근입니다.", "403"),
    NOT_FOUND("대상이 존재하지 않습니다.", "404"),
    INTERNAL_SERVER_ERROR("서버에 오류가 발생했습니다. 잠시 후 다시 시도해주세요.", "500");

    private final String message;

    private final String code;

    ResultCode(String message, String code) {
        this.message = message;
        this.code = code;
    }

}
