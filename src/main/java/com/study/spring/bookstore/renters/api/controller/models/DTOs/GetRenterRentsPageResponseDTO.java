package com.study.spring.bookstore.renters.api.controller.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@Schema(name = "GetRenterRentsPageResponse")
public record GetRenterRentsPageResponseDTO(
        @Schema(description = "renter id", example = "1")
        Integer id,
        @Schema(description = "renter name", example = "yuri alberto")
        String renterName,
        @Schema(description = "renter telephone", example = "988686097")
        String bookName,
        @Schema(description = "renter contact email", example = "yuri@alberto.com")
        LocalDate rentDate
) {
}

