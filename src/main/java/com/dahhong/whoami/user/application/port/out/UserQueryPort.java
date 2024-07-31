package com.dahhong.whoami.user.application.port.out;

import com.dahhong.whoami.user.domain.entity.User;

import java.util.Optional;

public interface UserQueryPort {

    Optional<User> findById(String userId);

}
