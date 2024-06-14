package com.study.spring.bookstore.books.api.controller.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@Schema(name = "GetBookDetailsResponse")
public record GetBookDetailsResponseDTO(
        @Schema(description = "book id", example = "1")
        Integer id,
        @Schema(description = "book name", example = "book name")
        String name,
        @Schema(description = "book author", example = "author name")
        String author,
        @Schema(description = "book available quantity", example = "1")
        Integer availableQuantity,
        @Schema(description = "boo total quantity", example = "1")
        Integer totalQuantity,
        @Schema(description = "book launch date", example = "10/12/1986")
        LocalDate launchDate,
        @Schema(description = "book publisher", example = "publisher")
        String publisherName
) {
}
