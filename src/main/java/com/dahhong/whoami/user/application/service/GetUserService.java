package com.dahhong.whoami.user.application.service;

import com.dahhong.whoami.global.exception.customException.NotFoundException;
import com.dahhong.whoami.user.application.port.in.GetUserUseCase;
import com.dahhong.whoami.user.application.port.out.UserQueryPort;
import com.dahhong.whoami.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUserService implements GetUserUseCase {

    private final UserQueryPort userQueryPort;

    @Override
    public User getUser(String userId) {
        return userQueryPort.findById(userId).orElseThrow(() -> new NotFoundException("유저를 찾을 수 없습니다."));
    }

    @Override
    public boolean isExistUser(String userId) {
        return userQueryPort.findById(userId).isPresent();
    }

    @Override
    public List<User> getAllUsers() {
        return userQueryPort.findAll();
    }

}
