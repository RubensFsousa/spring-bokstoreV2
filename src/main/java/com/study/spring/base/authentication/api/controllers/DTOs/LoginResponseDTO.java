package com.study.spring.base.authentication.api.controllers.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "LoginResponse")
public record LoginResponseDTO(
        @Schema(name = "username", example = "username")
        String username,
        @Schema(name = "token", example = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9")
        String token,
        @Schema(name = "role", example = "USER")
        String role
) {
}
