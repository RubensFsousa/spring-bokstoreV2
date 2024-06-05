package com.study.spring.base.authentication.api.controllers;

import com.study.spring.base.authentication.api.controllers.DTOs.LoginRequestDTO;
import com.study.spring.base.authentication.api.controllers.DTOs.LoginResponseDTO;
import com.study.spring.base.authentication.domain.services.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO request) {
        var response = authService.authenticate(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
