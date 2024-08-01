package com.dahhong.whoami.auth.application.service;

import com.dahhong.whoami.auth.domain.entity.UserDetailsImpl;
import com.dahhong.whoami.user.application.port.out.UserQueryPort;
import com.dahhong.whoami.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserQueryPort userQueryPort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userQueryPort.findById(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with username: " + username));
        return new UserDetailsImpl(user);
    }

}
