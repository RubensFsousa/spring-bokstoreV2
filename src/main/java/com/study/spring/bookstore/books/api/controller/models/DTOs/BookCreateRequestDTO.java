package com.study.spring.bookstore.books.api.controller.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@Schema(name = "BookCreateRequest")
public record BookCreateRequestDTO(
        @Schema(description = "book name", example = "The Lord of the Rings")
        String name,
        @Schema(description = "book author", example = "J.R.R. Tolkien")
        String author,
        @Schema(description = "available quantity of books to rent", example = "1")
        Integer availableQuantity,
        @Schema(description = "book launch date", example = "29/07/1954")
        LocalDate launchDate,
        @Schema(description = "book publisher id", example = "1")
        Integer publisherId
) {
}
