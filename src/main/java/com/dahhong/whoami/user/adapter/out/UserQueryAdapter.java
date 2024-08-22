package com.dahhong.whoami.user.adapter.out;

import com.dahhong.whoami.user.application.port.out.UserQueryPort;
import com.dahhong.whoami.user.domain.entity.User;
import com.dahhong.whoami.user.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserQueryAdapter implements UserQueryPort {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findById(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> findAll() { return userRepository.findAll(); }
}
