package vagnerlg.com.github.medicalservices.presentation.http.response.errors;

import jakarta.validation.constraints.NotNull;

public record Error(
        @NotNull
        String field,

        @NotNull
        String msg
) {
}
