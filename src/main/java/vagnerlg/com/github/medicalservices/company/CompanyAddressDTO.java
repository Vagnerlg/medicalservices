package vagnerlg.com.github.medicalservices.company;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CompanyAddressDTO(
        @NotNull UUID workerId
) {
}
