package com.study.spring.base.authentication.api.controllers.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "LoginResponse")
public record LoginResponseDTO(

        String username,
        String token,
        String role

) {
}
