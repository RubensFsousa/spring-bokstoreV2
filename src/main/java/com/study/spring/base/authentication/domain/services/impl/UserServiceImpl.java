package com.study.spring.base.authentication.domain.services.impl;

import com.study.spring.base.authentication.api.controllers.user.v1.models.DTOs.UserCreateRequest;
import com.study.spring.base.authentication.domain.models.entities.UserEntity;
import com.study.spring.base.authentication.domain.repositories.UserRepository;
import com.study.spring.base.authentication.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void create(UserCreateRequest request) {
        var user = toUserEntity(request);
        userRepository.save(user);
    }

    private UserEntity toUserEntity(UserCreateRequest request) {
        return UserEntity.builder().name(request.name()).password(request.password()).build();
    }
}
