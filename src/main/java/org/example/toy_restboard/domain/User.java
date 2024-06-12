package org.example.toy_restboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id", unique = true)
    private String userId;
    private String password;
    @Column(name = "reg_data")
    @CreationTimestamp
    private LocalDateTime regData;
    @Column(unique = true)
    private String email;
    private String name;
    private Integer birth;
    private Role role;
    @OneToMany(mappedBy = "user")
    private Set<Board> boards;
}
