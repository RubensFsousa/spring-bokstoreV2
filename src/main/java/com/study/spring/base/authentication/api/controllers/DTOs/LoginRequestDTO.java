package com.study.spring.base.authentication.api.controllers.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

@Schema(name = "loginRequest")
public record LoginRequestDTO(
        @Schema(description = "username.", example = "username")
        @NotEmpty
        String username,
        @Schema(description = "password.", example = "password")
        @NotEmpty
        String password
) implements Serializable {
}
