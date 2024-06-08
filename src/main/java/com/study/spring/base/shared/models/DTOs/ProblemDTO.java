package com.study.spring.base.shared.models.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Schema(name = "ProblemDTO")
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProblemDTO(
        @Schema(example = "0")
        Integer status,
        @Schema(example = "2000-01-01T00:00:00Z")
        LocalDateTime timestamp,
        @Schema(example = "https://localhost/invalid-data")
        String type,
        @Schema(example = "Invalid data")
        String title,
        @Schema(example = "One or more fields are invalid. Fill in correctly and try again.")
        String detail,
        @Schema(example = "One or more fields are invalid. Fill in correctly and try again.")
        String userMessage,
        @Schema(example = "List of objects or fields that generated or error (optional)")
        List<ProblemObjectDTO> objects
) {
}
