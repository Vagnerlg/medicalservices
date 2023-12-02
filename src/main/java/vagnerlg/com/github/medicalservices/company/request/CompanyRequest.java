package vagnerlg.com.github.medicalservices.company.request;

import jakarta.validation.constraints.NotNull;

public record CompanyRequest(
        @NotNull String name,
        String fileName
) {
}
