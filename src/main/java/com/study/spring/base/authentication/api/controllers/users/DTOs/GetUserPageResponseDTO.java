package com.study.spring.base.authentication.api.controllers.users.DTOs;

import com.study.spring.base.authentication.domain.models.enums.Roles;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "GetUserPageResponseDTO")
public record GetUserPageResponseDTO(
        @Schema(description = "user id", example = "1")
        Integer id,
        @Schema(description = "username", example = "username")
        String name,
        @Schema(description = "user permission", example = "ROLE")
        Roles role
) {
}
