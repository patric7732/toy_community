package org.example.toy_restboard.global.util.converter;

import org.example.toy_restboard.domain.user.entity.Role;
import org.springframework.core.convert.converter.Converter;

public class RoleRequestConverter implements Converter<String, Role> {
    @Override
    public Role convert(String source) {
        return Role.valueOf(source);
    }
}
