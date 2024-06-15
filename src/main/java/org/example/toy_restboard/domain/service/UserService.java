package org.example.toy_restboard.domain.service;

import lombok.RequiredArgsConstructor;
import org.example.toy_restboard.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
}
