package com.dahhong.whoami.user.application.service;

import com.dahhong.whoami.user.application.port.in.GetUserUseCase;
import com.dahhong.whoami.user.application.port.in.QuitUserUseCase;
import com.dahhong.whoami.user.application.port.out.UserCommandPort;
import com.dahhong.whoami.user.domain.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuitUserService implements QuitUserUseCase {

    private final GetUserUseCase getUserUseCase;

    private final UserCommandPort userCommandPort;

    @Override
    @Transactional
    public void quitUser(String userId) {
        User user = getUserUseCase.getUser(userId);
        userCommandPort.delete(user);
    }

}
