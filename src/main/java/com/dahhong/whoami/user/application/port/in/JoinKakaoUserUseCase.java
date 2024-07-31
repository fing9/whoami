package com.dahhong.whoami.user.application.port.in;

import com.dahhong.whoami.user.domain.entity.AuthType;

public interface JoinKakaoUserUseCase {

    void joinKakaoUser(String id, String nickname, String profileImage);

}
