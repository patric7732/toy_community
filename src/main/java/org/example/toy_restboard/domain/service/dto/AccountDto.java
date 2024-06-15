package org.example.toy_restboard.domain.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.toy_restboard.domain.entity.Role;
import org.example.toy_restboard.domain.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    private Long id;
    private String name;
    private String password;
    private String loginId;
    private Integer birth;
    private String email;
    private Role role;

    /**
     * Entity -> Dto
     */

    public static AccountDto toDto(User user) {
        return AccountDto.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .loginId(user.getLoginId())
                .birth(user.getBirth())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
