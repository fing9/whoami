package com.dahhong.whoami.auth.application.port.in;

import com.dahhong.whoami.auth.adapter.in.dto.KakaoCallbackResponseDto;

import java.net.URI;

public interface LoginKakaoUseCase {

    URI getKakaoOauthURI();

    KakaoCallbackResponseDto loginKakao(String code);

}
