package com.study.spring.base.authentication.domain.services;

import com.study.spring.base.authentication.api.controllers.user.v1.models.DTOs.UserCreateRequest;

public interface UserService {
    void create(UserCreateRequest request);
}
