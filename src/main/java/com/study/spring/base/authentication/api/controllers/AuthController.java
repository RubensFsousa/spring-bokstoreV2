package com.study.spring.base.authentication.api.controllers;

import com.study.spring.base.authentication.api.controllers.DTOs.LoginRequestDTO;
import com.study.spring.base.authentication.api.controllers.DTOs.LoginResponseDTO;
import com.study.spring.base.authentication.api.controllers.annotations.LoginEndPoint;
import com.study.spring.base.authentication.domain.services.AuthService;
import com.study.spring.base.shared.annotations.ApiController;
import com.study.spring.base.shared.annotations.OpenApiController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@OpenApiController(name = "Auth")
@ApiController(path = "/auth")
public class AuthController {

    private final AuthService authService;

    @LoginEndPoint
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO request) {
        var response = authService.authenticate(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
