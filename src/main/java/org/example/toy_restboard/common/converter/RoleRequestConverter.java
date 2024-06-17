package org.example.toy_restboard.common.converter;

import jakarta.persistence.Convert;
import org.example.toy_restboard.domain.entity.Role;
import org.springframework.core.convert.converter.Converter;

import java.lang.annotation.Annotation;

public class RoleRequestConverter implements Converter<String, Role> {
    @Override
    public Role convert(String source) {
        return Role.valueOf(source);
    }
}
