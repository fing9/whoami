package com.dahhong.whoami.auth.application.port.in;

import com.dahhong.whoami.auth.application.service.dto.TokenRefreshResponseDto;

public interface KakaoTokenRefreshUseCase {

    TokenRefreshResponseDto refresh(String refreshToken);

}
