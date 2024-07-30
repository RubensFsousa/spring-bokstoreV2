package com.study.spring.base.authentication.domain.mappers;

import com.study.spring.base.authentication.api.controllers.users.DTOs.GetUserDetailsResponseDTO;
import com.study.spring.base.authentication.domain.models.entities.UserEntity;

public interface UserMapper {
    GetUserDetailsResponseDTO toGetUserDetailsResponseDTO(UserEntity user);
}
