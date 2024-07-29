package com.study.spring.bookstore.renters.api.controller.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

@Builder
@Schema(name = "RenterCreateRequest")
public record RenterCreateRequestDTO(
        @Schema(description = "renter name", example = "yuri alberto")
        @Size(max = 50, min = 1)
        @NotEmpty
        String name,
        @Schema(description = "renter contact email", example = "yuri@alberto.com")
        @Email
        @Size(max = 50, min = 1)
        @NotEmpty
        String email,
        @Schema(description = "renter contact telephone", example = "85 98563-7123")
        @Pattern(regexp = "^(\\+?\\d{1,3}[\\s.-]?)?\\(?\\d{2}\\)?[\\s.-]?\\d{3,4}[\\s.-]?\\d{4,6}$")
        @NotEmpty
        String telephone,
        @Schema(description = "renter address", example = "rua x, 100 - bairro y, fortaleza - CE")
        @Size(max = 255, min = 1)
        String address,
        @Schema(description = "renter cpf", example = "123.123.123-12")
        @Pattern(regexp = "^([0-9]{2}[.]?[0-9]{3}[.]?[0-9]{3}/?[0-9]{4}-?[0-9]{2})|([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}-?[0-9]{2})$")
        String cpf
) {
}
