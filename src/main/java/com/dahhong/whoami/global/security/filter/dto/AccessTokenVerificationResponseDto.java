package com.dahhong.whoami.global.security.filter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenVerificationResponseDto {

    private Long id;

    private Integer expires_in;

    private Integer app_id;

}
