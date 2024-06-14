package org.example.toy_restboard.domain.entity;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("관리자"), MEMBER("멤버");
    String Description;

    Role(String description) {
        Description = description;
    }
}
