package com.study.spring.bookstore.rents.api.controllers.models.DTOs;

import com.study.spring.bookstore.rents.domain.enums.RentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@Schema(name = "GetRentPageResponse")
public record GetRentPageResponseDTO(
        @Schema(description = "rent id", example = "1")
        Integer id,
        @Schema(description = "renter name", example = "yuri")
        String renterName,
        @Schema(description = "book name", example = "Lord of the rings")
        String bookName,
        @Schema(description = "rent status", example = "DELAYED")
        @Enumerated(EnumType.STRING)
        RentStatus status,
        @Schema(description = "devolution date")
        LocalDate devolutionDate,
        @Schema(description = "deadline dade")
        LocalDate deadLineDate,
        @Schema(description = "rent date")
        LocalDate rentDate
) {
}
