package com.study.spring.base.authentication.domain.services.impl;

import com.study.spring.base.authentication.api.controllers.auth.DTOs.LoginRequestDTO;
import com.study.spring.base.authentication.api.controllers.auth.DTOs.LoginResponseDTO;
import com.study.spring.base.authentication.domain.models.entities.UserEntity;
import com.study.spring.base.authentication.domain.services.AuthService;
import com.study.spring.base.authentication.domain.services.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Override
    public LoginResponseDTO authenticate(LoginRequestDTO request) {
        var usernamePassWord = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        var auth = authenticationManager.authenticate(usernamePassWord);
        var user = (UserEntity) auth.getPrincipal();
        var token = tokenService.generateToken(user);
        return buildLoginResponseDTO(token, user);
    }

    private LoginResponseDTO buildLoginResponseDTO(String token, UserEntity user) {
        return LoginResponseDTO.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }

}
