package com.study.spring.base.authentication.api.controllers.user.v1.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Schema(name = "UserCreateRequest")
public record UserCreateRequest(
        @Schema(description = "User name.", example = "Name")
        @Size(max = 100)
        @NotEmpty(message = "Nome não pode ser nulo")
        @NotBlank
        String name,
        @Schema(description = "User  password.", example = "qwe123*")
        @Size(max = 100, min = 6)
        @NotEmpty
        @NotBlank
        String password) {
}
