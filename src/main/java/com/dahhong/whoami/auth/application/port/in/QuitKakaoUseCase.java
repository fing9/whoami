package com.dahhong.whoami.auth.application.port.in;

import com.dahhong.whoami.auth.application.service.dto.QuitKakaoResponseDto;

public interface QuitKakaoUseCase {

    QuitKakaoResponseDto quitKakao(String accessToken);

}
