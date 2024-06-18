package com.study.spring.bookstore.publishers.api.controller.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(name = "PublisherCreateRequest")
public record PublisherCreateRequestDTO(
        @Schema(description = "publisher name", example = "HarperCollins")
        @Size(max = 50, min = 1)
        @NotEmpty
        String name,
        @Schema(description = "publisher contact email", example = "consumercare@harpercollins.com")
        @Email
        @Size(max = 50, min = 1)
        @NotEmpty
        String email,
        @Schema(description = "publisher contact telephone", example = "212-207-7000")
        @Pattern(regexp = "^(\\+?\\d{1,3}[\\s.-]?)?\\(?\\d{2}\\)?[\\s.-]?\\d{3,4}[\\s.-]?\\d{4,6}$")
        @NotEmpty
        String telephone,
        @Schema(description = "publisher web site", example = "harpercollins.com")
        @Size(max = 50)
        String site
) {
}
