package com.study.spring.base.authentication.domain.services;

import com.study.spring.base.authentication.api.controllers.DTOs.LoginRequestDTO;
import com.study.spring.base.authentication.api.controllers.DTOs.LoginResponseDTO;

public interface AuthService {
    LoginResponseDTO authenticate(LoginRequestDTO request);
}
