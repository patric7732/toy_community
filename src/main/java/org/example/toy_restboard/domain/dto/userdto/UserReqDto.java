package org.example.toy_restboard.domain.dto.userdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.toy_restboard.domain.entity.Role;

public class UserReqDto {

    @Getter
    @Setter
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
        @Pattern(regexp = "^[0-9]{6}$") // 6자리
        private Integer birth;
        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z]*$") // 영어만 가능
        private Role role;
    }
}
