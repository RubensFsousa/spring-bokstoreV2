package com.study.spring.base.authentication.api.controllers.users.DTOs;

import com.study.spring.base.authentication.domain.models.enums.Roles;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
@Schema(name = "UpdateUserRequest")
public record UpdateUserRequestDTO(
        @Schema(description = "user id", example = "1")
        @Positive
        @NotNull
        Integer id,
        @Schema(description = "username", example = "username")
        @Size(max = 30, min = 1)
        @NotEmpty
        String name,
        @Schema(description = "user email", example = "user@example.com")
        @Size(max = 30)
        @Email
        @NotEmpty
        String email,
        @Schema(description = "user permission", example = "ROLE")
        @NotNull
        Roles role
) {
}
