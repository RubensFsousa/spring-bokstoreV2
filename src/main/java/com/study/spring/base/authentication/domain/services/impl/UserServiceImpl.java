package com.study.spring.base.authentication.domain.services.impl;

import com.study.spring.base.authentication.api.controllers.users.DTOs.CreateUserRequestDTO;
import com.study.spring.base.authentication.api.controllers.users.DTOs.GetUserDetailsResponseDTO;
import com.study.spring.base.authentication.api.controllers.users.DTOs.UpdateUserRequestDTO;
import com.study.spring.base.authentication.domain.mappers.UserMapper;
import com.study.spring.base.authentication.domain.models.entities.UserEntity;
import com.study.spring.base.authentication.domain.repositories.UserRepository;
import com.study.spring.base.authentication.domain.services.UserService;
import com.study.spring.base.shared.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public void createUser(CreateUserRequestDTO request) {
        userRepository.save(UserEntity.builder()
                .username(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())
                .build());
    }

    @Override
    public void updateUser(UpdateUserRequestDTO request) {
        var user = getUserByIdOrThrow(request.id());
        userRepository.save(user.toBuilder()
                .username(request.name())
                .email(request.email())
                .role(request.role())
                .build());
    }

    @Override
    public GetUserDetailsResponseDTO getUserById(Integer id) {
        var user = getUserByIdOrThrow(id);
        return userMapper.toGetUserDetailsResponseDTO(user);
    }

    private UserEntity getUserByIdOrThrow(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserNotFound"));
    }
}
