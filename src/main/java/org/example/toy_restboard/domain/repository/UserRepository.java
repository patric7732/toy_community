package org.example.toy_restboard.domain.repository;

import org.example.toy_restboard.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLoginId(String loginId);
}
