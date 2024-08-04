package com.study.spring.bookstore.rents.api.controllers.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "GetRenterRentsPageResponse")
public record GetRenterRentsPageResponseDTO(
        @Schema(description = "rent id", example = "1")
        Integer id,
        @Schema(description = "renter name", example = "yuri")
        String name,
        @Schema(description = "total renter rents", example = "2")
        Integer totalRents,
        @Schema(description = "total active renter rents", example = "1")
        Integer activeRents
) {

}
