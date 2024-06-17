package org.example.toy_restboard.domain.dto.userdto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.toy_restboard.domain.entity.Role;
import org.hibernate.validator.constraints.Range;

public class UserReqDto {

    @Getter
    @Setter
    @ToString
    public static class LoginReqDto {
        private String loginId;
        private String password;

    }

    @Getter
    @Setter
    public static class JoinReqDto{
        // 유효성 검사
        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z0-9가-힣]{2,10}$") // 영어, 한글, 숫자를 2~10자까지 받을수 있다
        private String name;
        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$") // //영문숫자만 가능
        private String loginId;
        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$") //영문숫자만 가능 나중에 8~20까지만 받기로
        private String password;
        @NotEmpty
        @Email
        private String email;
        @NotNull
        private Integer birth;

        private Role role;
    }
}
