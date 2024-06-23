package com.study.spring.bookstore.rents.api.controllers.models.DTOs;

import com.study.spring.bookstore.rents.api.controllers.models.constraints.MaxThirtyDays;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@Schema(name = "RentCreateRequest")
public record RentCreateRequestDTO(
        @Schema(description = "renter id", example = "1")
        @Positive
        @NotNull
        Integer renterId,
        @Schema(description = "renter id", example = "1")
        @Positive
        @NotNull
        Integer bookId,
        @Schema(description = "deadline for devolution")
        @FutureOrPresent
        @MaxThirtyDays
        LocalDate Deadline
) {
}
