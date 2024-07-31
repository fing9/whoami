package com.dahhong.whoami.user.infrastructure.repository;

import com.dahhong.whoami.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
