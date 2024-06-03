package com.study.spring.base.authentication.domain.services;

import com.study.spring.base.authentication.domain.models.entities.UserEntity;

public interface UserService {
    UserEntity getUserByName(String username);
}
