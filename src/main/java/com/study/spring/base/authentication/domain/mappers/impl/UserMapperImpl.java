package com.study.spring.base.authentication.domain.mappers.impl;

import com.study.spring.base.authentication.api.controllers.users.DTOs.GetUserDetailsResponseDTO;
import com.study.spring.base.authentication.api.controllers.users.DTOs.GetUserPageResponseDTO;
import com.study.spring.base.authentication.domain.mappers.UserMapper;
import com.study.spring.base.authentication.domain.models.entities.UserEntity;
import com.study.spring.base.shared.models.PageResponse;
import org.springframework.data.domain.Page;
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

    @Override
    public PageResponse<GetUserPageResponseDTO> toUserPageResponseDTO(Page<UserEntity> usersPage) {
        var content = usersPage.getContent().stream().map(user -> GetUserPageResponseDTO.builder()
                .id(user.getId())
                .name(user.getUsername())
                .role(user.getRole())
                .build()).toList();
        
        return new PageResponse<>(
                content,
                usersPage.getNumber(),
                usersPage.getSize(),
                usersPage.getTotalElements(),
                usersPage.getTotalPages()
                
        );
    }
}
