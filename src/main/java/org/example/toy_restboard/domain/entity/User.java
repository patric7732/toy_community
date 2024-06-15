package org.example.toy_restboard.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.toy_restboard.domain.service.dto.UserJoinDto;

import java.util.List;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards;


    public static User toEntity(UserJoinDto joinDto) {
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
