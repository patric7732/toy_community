package org.example.toy_restboard.domain.dto.userdto;

import lombok.Getter;
import lombok.Setter;
import org.example.toy_restboard.common.util.CustomDateUtil;
import org.example.toy_restboard.domain.entity.User;

public class UserRespDto {
    @Getter
    @Setter
    private static class LoginRespDto{
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
        private String loingId;
        private String name;


        public JoinRespDto(User user) {
            this.id = user.getId();
            this.loingId = user.getLoginId();
            this.name = user.getName();

        }
    }
}
