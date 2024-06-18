package org.example.toy_restboard.global.config.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.example.toy_restboard.global.config.security.LoginUser;
import org.example.toy_restboard.domain.user.entity.Role;
import org.example.toy_restboard.domain.user.entity.User;

import java.util.Date;

@Slf4j
public class JwtProcess {
    // 토큰 생성
    public static String create(LoginUser loginUser) {
        String jwtToken = JWT.create()
                .withSubject("board")
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtVO.EXPIRATION_TIME))
                .withClaim("id", loginUser.getUser().getId())
                .withClaim("role", loginUser.getUser().getRole().name())
                .withClaim("loginId",loginUser.getUser().getLoginId())
                .sign(Algorithm.HMAC512(JwtVO.SECRET));
        return JwtVO.TOKEN_PREFIX + jwtToken;
    }

    // 토큰 검증
    public static LoginUser verify(String token) {
        DecodedJWT decodedJwt = JWT.require(Algorithm.HMAC512(JwtVO.SECRET)).build().verify(token);
        Long id = decodedJwt.getClaim("id").asLong();
        String role = decodedJwt.getClaim("role").asString();
        String loginId = decodedJwt.getClaim("loginId").asString();
        User user = User.builder().id(id).role(Role.valueOf(role)).loginId(loginId).build();
        return new LoginUser(user);

    }
}
