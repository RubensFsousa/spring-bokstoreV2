package com.study.spring.base.authentication.domain.mappers;

import com.study.spring.base.authentication.api.controllers.users.DTOs.GetUserDetailsResponseDTO;
import com.study.spring.base.authentication.api.controllers.users.DTOs.GetUserPageResponseDTO;
import com.study.spring.base.authentication.domain.models.entities.UserEntity;
import com.study.spring.base.shared.models.PageResponse;
import org.springframework.data.domain.Page;

public interface UserMapper {
    GetUserDetailsResponseDTO toGetUserDetailsResponseDTO(UserEntity user);

    PageResponse<GetUserPageResponseDTO> toUserPageResponseDTO(Page<UserEntity> usersPage);
}
