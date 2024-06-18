package org.example.toy_restboard.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.toy_restboard.domain.user.dto.UserReqDto;
import org.example.toy_restboard.global.exception.CustomApiException;
import org.example.toy_restboard.domain.user.entity.User;
import org.example.toy_restboard.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

import static org.example.toy_restboard.domain.user.dto.UserRespDto.*;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public JoinRespDto join(@Validated UserReqDto.JoinReqDto userDto) {
        // 동일 유저네임 존재 검사
        Optional<User> _user = userRepository.findByLoginId(userDto.getLoginId());
        if(_user.isPresent()) {
            throw new CustomApiException("동일한 username이 존재합니다.");
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User userPs = userRepository.save(User.toEntity(userDto));

        return new JoinRespDto(userPs);
    }
}
