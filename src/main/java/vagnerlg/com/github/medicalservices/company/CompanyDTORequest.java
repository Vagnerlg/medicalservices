package vagnerlg.com.github.medicalservices.company;

import jakarta.validation.constraints.NotNull;

public record CompanyDTORequest(
        @NotNull String name,
        String file
) {
}
