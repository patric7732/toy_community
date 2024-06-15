package org.example.toy_restboard.domain.service.dto;

import lombok.*;
import org.example.toy_restboard.domain.entity.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserJoinDto {
    private String name;
    private String loginId;
    private String password;
    private String email;
    private Integer birth;
    private Role role;


}
