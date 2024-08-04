package com.study.spring.base.authentication.api.controllers.users.DTOs;

import com.study.spring.base.authentication.domain.models.enums.Roles;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(name = "GetUserDetailsResponse")
public record GetUserDetailsResponseDTO(
        @Schema(description = "user id", example = "1")
        @Positive
        @NotNull
        Integer id,
        @Schema(description = "username", example = "username")
        @Size(max = 30, min = 1)
        @NotEmpty
        String name,
        @Schema(description = "user permission", example = "ROLE")
        @NotNull
        Roles role
) {
}
