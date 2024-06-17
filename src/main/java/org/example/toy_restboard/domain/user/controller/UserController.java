package org.example.toy_restboard.domain.user.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.example.toy_restboard.domain.user.dto.UserReqDto;
import org.example.toy_restboard.domain.user.dto.UserRespDto;
import org.example.toy_restboard.domain.user.service.UserService;
import org.example.toy_restboard.global.dto.ResponseDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@Valid @RequestBody UserReqDto.JoinReqDto joinReqDto, BindingResult bindingResult) {
        UserRespDto.JoinRespDto userRespDto = userService.join(joinReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1,"회원 가입 성공",userRespDto), HttpStatus.CREATED);

    }
}
