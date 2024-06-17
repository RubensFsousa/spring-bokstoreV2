package com.study.spring.bookstore.books.api.controller.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@Schema(name = "BookCreateRequest")
public record BookCreateRequestDTO(
        @Schema(description = "book name", example = "The Lord of the Rings")
        @Size(max = 50, min = 1)
        @NotEmpty
        String name,
        @Schema(description = "book author", example = "J.R.R. Tolkien")
        @Size(max = 50, min = 1)
        @NotEmpty
        String author,
        @Schema(description = "total books quantity", example = "1")
        @Min(1)
        @Max(1000000)
        @NotNull
        Integer totalQuantity,
        @Schema(description = "book launch date", example = "29/07/1954")
        @PastOrPresent
        LocalDate launchDate,
        @Schema(description = "book publisher id", example = "1")
        @NotNull
        Integer publisherId
) {
}
