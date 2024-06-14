package com.study.spring.base.authentication.domain.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Roles {

    ADMIN(Name.ADMIN),
    VISITOR(Name.VISITOR);

    private final String name;

    public interface Name {
        String ADMIN = "ADMIN";
        String VISITOR = "VISITOR";
    }
}
