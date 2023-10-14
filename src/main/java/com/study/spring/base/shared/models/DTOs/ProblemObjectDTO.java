package com.study.spring.base.shared.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ProblemObject")
public record ProblemObjectDTO(
        @Schema(example = "Name")
        String name,
        @Schema(example = "Name not be null")
        String message
) {
}
