package org.example.toy_restboard.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import static org.example.toy_restboard.domain.dto.userdto.UserReqDto.*;

@Table(name = "users")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(of = {"id","loginId","password","email","name","birth","role"})
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
        Role role = joinDto.getRole();
        if (role == null) {
            role = Role.ROLE_USER;
            joinDto.setRole(role); // 필요에 따라 joinDto의 Role을 설정해줍니다.
        }
        return User.builder()
                .loginId(joinDto.getLoginId())
                .password(joinDto.getPassword())
                .email(joinDto.getEmail())
                .name(joinDto.getName())
                .birth(joinDto.getBirth())
                .role(role)
                .build();
    }
}
