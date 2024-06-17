package org.example.toy_restboard.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.toy_restboard.domain.dto.userdto.UserReqDto;

import static org.example.toy_restboard.domain.dto.userdto.UserReqDto.*;

@Table(name = "users")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_Id", unique = true)
    private String loginId;

    private String password;

    @Column(unique = true)
    private String email;

    private String name;

    private Integer birth;
    @Enumerated(EnumType.STRING)
    private Role role;


    public static User toEntity(JoinReqDto joinDto) {
        return User.builder()
                .loginId(joinDto.getLoginId())
                .password(joinDto.getPassword())
                .email(joinDto.getEmail())
                .name(joinDto.getName())
                .birth(joinDto.getBirth())
                .role(joinDto.getRole())
                .build();
    }
}
