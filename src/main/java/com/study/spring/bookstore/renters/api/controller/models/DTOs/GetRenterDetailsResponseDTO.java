package com.study.spring.bookstore.renters.api.controller.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "GetRenterDetailsResponse")
public record GetRenterDetailsResponseDTO(
        @Schema(description = "renter id", example = "1")
        Integer id,
        @Schema(description = "renter name", example = "yuri alberto")
        String name,
        @Schema(description = "renter contact email", example = "yuri@alberto.com")
        String email,
        @Schema(description = "renter contact telephone", example = "85 98563-7123")
        String telephone,
        @Schema(description = "renter address", example = "rua x, 100 - bairro y, fortaleza - CE")
        String address,
        @Schema(description = "renter cpf", example = "123.123.123-12")
        String cpf
) {
}
