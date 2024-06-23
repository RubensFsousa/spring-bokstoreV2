package com.study.spring.bookstore.rents.api.controllers.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "GetMostRentedBookResponse")
public record GetMostRentedBookResponseDTO(
        @Schema(description = "book name", example = "the lord of rings")
        String bookName,
        @Schema(description = "number of times book was rented")
        Integer rentedNumber
) {
}
