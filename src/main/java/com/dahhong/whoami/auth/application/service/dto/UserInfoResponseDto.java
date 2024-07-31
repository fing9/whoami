package com.dahhong.whoami.auth.application.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponseDto {

    private Long id;

    private KakaoAccount kakao_account;

}
