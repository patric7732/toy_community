package org.example.toy_restboard.web.controller;

import lombok.RequiredArgsConstructor;
import org.example.toy_restboard.domain.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
}
