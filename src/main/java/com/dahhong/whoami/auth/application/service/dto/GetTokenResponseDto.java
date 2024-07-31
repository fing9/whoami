package com.dahhong.whoami.auth.application.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTokenResponseDto {

    private String token_type;

    private String access_token;

    private String id_token;

    private Integer expires_in;

    private String refresh_token;

    private String refresh_token_expires_in;

    private String scope;

}
