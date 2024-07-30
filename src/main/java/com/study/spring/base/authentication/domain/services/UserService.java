package com.study.spring.base.authentication.domain.services;

import com.study.spring.base.authentication.api.controllers.users.DTOs.CreateUserRequestDTO;
import com.study.spring.base.authentication.api.controllers.users.DTOs.GetUserDetailsResponseDTO;
import com.study.spring.base.authentication.api.controllers.users.DTOs.UpdateUserRequestDTO;
import com.study.spring.base.authentication.domain.models.entities.UserEntity;

public interface UserService {
    void createUser(CreateUserRequestDTO request);

    void updateUser(UpdateUserRequestDTO request);

    GetUserDetailsResponseDTO getUserById(Integer id);
}
