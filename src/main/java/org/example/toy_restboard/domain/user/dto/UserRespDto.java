package org.example.toy_restboard.domain.user.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.toy_restboard.domain.user.entity.User;
import org.example.toy_restboard.global.util.CustomDateUtil;

public class UserRespDto {
    @Getter
    @Setter
    public static class LoginRespDto{
        private Long id;
        private String loginId;
        private String createAt;

        public LoginRespDto(User user) {
            this.id = user.getId();
            this.loginId = user.getLoginId();
            this.createAt = CustomDateUtil.toStringFormat(user.getCreatedAt());
        }
    }

    @Getter
    @Setter
    public static class JoinRespDto{
        private Long id;
        private String loginId;
        private String name;


        public JoinRespDto(User user) {
            this.id = user.getId();
            this.loginId = user.getLoginId();
            this.name = user.getName();

        }
    }
}
