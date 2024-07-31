package com.dahhong.whoami.user.application.port.in;

import com.dahhong.whoami.user.domain.entity.User;

import java.util.Optional;

public interface GetUserUseCase {

    User getUser(String userId);

    boolean isExistUser(String userId);

}