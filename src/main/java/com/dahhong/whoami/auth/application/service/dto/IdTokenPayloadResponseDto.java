package com.dahhong.whoami.auth.application.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdTokenPayloadResponseDto {

    private String iss;

    private String aud;

    private String sub;

    private Integer iat;

    private Integer exp;

    private String nonce;

    private Integer auth_time;

}
