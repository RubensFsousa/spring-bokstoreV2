package com.study.spring.bookstore.renters.api.controller.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "GetRenterPageResponse")
public record GetRenterPageResponseDTO(
        @Schema(description = "renter id", example = "1")
        Integer id,
        @Schema(description = "renter name", example = "yuri alberto")
        String name,
        @Schema(description = "renter telephone", example = "988686097")
        String telephone,
        @Schema(description = "renter contact email", example = "yuri@alberto.com")
        String email
) {
}
