package com.study.spring.base.authentication.domain.services;

import com.study.spring.base.authentication.api.controllers.auth.DTOs.LoginRequestDTO;
import com.study.spring.base.authentication.api.controllers.auth.DTOs.LoginResponseDTO;

public interface AuthService {
    LoginResponseDTO authenticate(LoginRequestDTO request);
}
