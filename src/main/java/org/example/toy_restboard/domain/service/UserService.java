package org.example.toy_restboard.domain.service;

import lombok.RequiredArgsConstructor;
import org.example.toy_restboard.domain.entity.User;
import org.example.toy_restboard.domain.repository.UserRepository;
import org.example.toy_restboard.domain.service.dto.UserJoinDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(UserJoinDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = User.toEntity(userDto);
        userRepository.save(user);
    }
}
