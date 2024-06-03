package com.study.spring.base.authentication.domain.services.impl;

import com.study.spring.base.authentication.domain.models.entities.UserEntity;
import com.study.spring.base.authentication.domain.repositories.UserRepository;
import com.study.spring.base.authentication.domain.services.UserService;
import com.study.spring.base.shared.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity getUserByName(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("UserNotFound"));
    }

}
