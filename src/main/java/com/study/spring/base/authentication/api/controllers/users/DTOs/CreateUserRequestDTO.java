package com.study.spring.base.authentication.api.controllers.users.DTOs;

import com.study.spring.base.authentication.domain.models.enums.Roles;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(name = "CreateUserRequest")
public record CreateUserRequestDTO(
        @Schema(description = "username", example = "username")
        @Size(max = 30, min = 1)
        @NotEmpty
        String name,
        @Schema(description = "user email", example = "user@example.com")
        @Size(max = 30)
        @Email
        @NotEmpty
        String email,
        @Schema(description = "user password", example = "password123")
        @Size(max = 100, min = 1)
        @NotEmpty
        String password,
        @Schema(description = "user permission", example = "ROLE")
        @NotNull
        Roles role
) {
}
