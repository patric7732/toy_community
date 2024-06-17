package org.example.toy_restboard.web.controller.user;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.example.toy_restboard.domain.dto.ResponseDto;
import org.example.toy_restboard.domain.dto.userdto.UserReqDto;
import org.example.toy_restboard.domain.dto.userdto.UserRespDto;
import org.example.toy_restboard.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.example.toy_restboard.domain.dto.userdto.UserReqDto.*;
import static org.example.toy_restboard.domain.dto.userdto.UserRespDto.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@Validated @RequestBody JoinReqDto joinReqDto, BindingResult bindingResult) {
        JoinRespDto userRespDto = userService.join(joinReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1,"회원 가입 성공",joinReqDto), HttpStatus.CREATED);

    }
}
