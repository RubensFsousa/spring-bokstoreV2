package com.study.spring.base.shared.models.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ProblemDTO")
//inclue  no json apenas as propiedades nao nulas
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProblemDTO() {
}
