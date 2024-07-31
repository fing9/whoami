package com.dahhong.whoami.user.application.port.out;

import com.dahhong.whoami.user.domain.entity.User;

public interface UserCommandPort {

    User save(User user);

    void delete(User user);

}
