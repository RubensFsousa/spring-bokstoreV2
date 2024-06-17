package com.study.spring.bookstore.publishers.api.controller.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "GetPublisherDetailsResponse")
public record GetPublisherDetailsResponseDTO(
        @Schema(description = "publisher id", example = "1")
        Integer id,
        @Schema(description = "publisher name", example = "HarperCollins")
        String name,
        @Schema(description = "publisher contact email", example = "consumercare@harpercollins.com")
        String email,
        @Schema(description = "publisher contact telephone", example = "212-207-7000")
        String telephone,
        @Schema(description = "publisher web site", example = "harpercollins.com")
        String site
) {
}
