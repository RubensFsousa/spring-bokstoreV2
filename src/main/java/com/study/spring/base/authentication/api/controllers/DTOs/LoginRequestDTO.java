package com.study.spring.base.authentication.api.controllers.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

@Schema(name = "loginRequest")
public record LoginRequestDTO(
        @NotEmpty
        String username,
        @NotEmpty
        String password

) {
}
