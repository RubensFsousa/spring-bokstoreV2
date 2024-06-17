package com.study.spring.bookstore.publishers.api.controller.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "GetPublisherPageResponse")
public record GetPublisherPageResponseDTO(
        @Schema(description = "publisher id", example = "1")
        Integer id,
        @Schema(description = "publisher name", example = "HarperCollins")
        String name
) {
}
