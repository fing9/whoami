package com.dahhong.whoami.user.application.service;

import com.dahhong.whoami.user.application.port.in.GetUserUseCase;
import com.dahhong.whoami.user.application.port.in.JoinKakaoUserUseCase;
import com.dahhong.whoami.user.application.port.out.UserCommandPort;
import com.dahhong.whoami.user.domain.entity.AuthType;
import com.dahhong.whoami.user.domain.entity.Role;
import com.dahhong.whoami.user.domain.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinKakaoUserService implements JoinKakaoUserUseCase {

    private final UserCommandPort userCommandPort;

    private final GetUserUseCase getUserUseCase;

    @Override
    @Transactional
    public void joinKakaoUser(String id, String nickname, String profileImage) {
        if (getUserUseCase.isExistUser(id)) {
            return;
        }
        User user = User.of(id, Role.USER, AuthType.KAKAO, nickname, profileImage);
        userCommandPort.save(user);
    }

}
