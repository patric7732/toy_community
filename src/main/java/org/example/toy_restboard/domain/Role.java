package org.example.toy_restboard.domain;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN(1), MEMBER(2);
    private final Integer level;
    Role(Integer level) {
        this.level = level;
    }

}
