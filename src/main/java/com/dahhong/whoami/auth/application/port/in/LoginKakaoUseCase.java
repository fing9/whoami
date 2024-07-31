package com.dahhong.whoami.auth.application.port.in;

import java.net.URI;

public interface LoginKakaoUseCase {

    URI getKakaoOauthURI();

    String loginKakao(String code);

}
