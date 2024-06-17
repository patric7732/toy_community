package org.example.toy_restboard.domain.user.entity;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_ADMIN("관리자"), ROLE_USER("사용자");
    String Description;

    Role(String description) {
        Description = description;
    }
}
