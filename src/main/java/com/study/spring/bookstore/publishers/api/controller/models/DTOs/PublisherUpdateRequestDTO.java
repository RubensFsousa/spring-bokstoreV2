package com.study.spring.bookstore.publishers.api.controller.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record PublisherUpdateRequestDTO(
        @Schema(description = "publisher id", example = "1")
        @Positive
        @NotNull
        Integer id,
        @Schema(description = "publisher name", example = "HarperCollins")
        @Size(max = 50, min = 1)
        @NotEmpty
        String name,
        @Schema(description = "publisher contact email", example = "consumercare@harpercollins.com")
        @Size(max = 50, min = 1)
        @NotEmpty
        String email,
        @Schema(description = "publisher contact telephone", example = "212-207-7000")
        @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")
        @NotEmpty
        String telephone,
        @Schema(description = "publisher web site", example = "harpercollins.com")
        @Size(max = 50)
        String site
) {
}
