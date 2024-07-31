package com.dahhong.whoami.user.adapter.out;

import com.dahhong.whoami.user.application.port.out.UserCommandPort;
import com.dahhong.whoami.user.domain.entity.User;
import com.dahhong.whoami.user.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserCommandAdapter implements UserCommandPort {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

}
