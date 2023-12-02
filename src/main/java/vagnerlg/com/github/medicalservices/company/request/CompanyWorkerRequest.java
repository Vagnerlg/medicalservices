package vagnerlg.com.github.medicalservices.company.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CompanyWorkerRequest(
        @NotNull UUID workerId
) {
}
