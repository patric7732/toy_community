package org.example.toy_restboard.common.security.jwt;

public interface JwtVO {
    // 프로젝트 배포 시 환경변수로 옮겨야 함 지금은 테스트용
    String SECRET = "토이프로젝트123";
    int EXPIRATION_TIME = 1000 * 60 * 60 * 60 * 24; // 하루
    String TOKEN_PREFIX = "Bearer ";
    String HEADER = "Authorization";
}
