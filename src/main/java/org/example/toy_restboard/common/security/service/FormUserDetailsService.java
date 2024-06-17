package org.example.toy_restboard.common.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.toy_restboard.domain.entity.User;
import org.example.toy_restboard.repository.UserRepository;
import org.example.toy_restboard.domain.dto.userdto.LoginUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("userDetailsService")
@Slf4j
public class FormUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(username).orElse(null);
        log.info("user: {}", user);
        if(user == null) {
            throw new UsernameNotFoundException("not found LoginId: " + username);
        }
        return new LoginUser(user);
    }
}
