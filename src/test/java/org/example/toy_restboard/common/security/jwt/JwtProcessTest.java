package org.example.toy_restboard.common.security.jwt;

import org.example.toy_restboard.domain.dto.userdto.LoginUser;
import org.example.toy_restboard.domain.entity.Role;
import org.example.toy_restboard.domain.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JwtProcessTest {

    @Test
    void create_test() throws Exception {
        // given
        User user = User.builder().id(1L).role(Role.ROLE_USER).build();
        LoginUser loginUser = new LoginUser(user);

        // when
        String jwtToken = JwtProcess.create(loginUser);
        System.out.println("jwtToken = " + jwtToken);

        // then
        Assertions.assertTrue(jwtToken.startsWith(JwtVO.TOKEN_PREFIX));
    }

    @Test
    void verify_test() throws Exception {
        // given
        String jwtToken = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJib2FyZCIsImV4cCI6MTcxOTQ5MDU4OSwiaWQiOjEsInJvbGUiOiJST0xFX1VTRVIifQ.wKbGZ1DRraMhI-hbsFq79qWoYcRbeQNjjWWYE2bZB3F5_bxhhYhnPtfczdQEF7nDEypWMbabqTFRgFMfWtSJCA";

        // when
        LoginUser loginUser = JwtProcess.verify(jwtToken);
        System.out.println("loginUser.getUser().getId() = " + loginUser.getUser().getId());

        // then
        org.assertj.core.api.Assertions.assertThat(loginUser.getUser().getId()).isEqualTo(1L);
    }

}