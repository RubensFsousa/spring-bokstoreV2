package com.study.spring.base.authentication.domain.mappers.impl;

import com.study.spring.base.authentication.api.controllers.users.DTOs.GetUserDetailsResponseDTO;
import com.study.spring.base.authentication.domain.mappers.UserMapper;
import com.study.spring.base.authentication.domain.models.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public GetUserDetailsResponseDTO toGetUserDetailsResponseDTO(UserEntity user) {
        return GetUserDetailsResponseDTO
                .builder()
                .id(user.getId())
                .name(user.getUsername())
                .role(user.getRole())
                .build();
    }
}
