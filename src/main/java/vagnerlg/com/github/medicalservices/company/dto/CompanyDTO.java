package vagnerlg.com.github.medicalservices.company.dto;

import jakarta.validation.constraints.NotNull;

public record CompanyDTO(
        @NotNull String name,
        String file
) {
}
