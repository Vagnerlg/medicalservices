package vagnerlg.com.github.medicalservices.presentation.http.response.errors;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record Errors(
        @NotEmpty
        List<Error> errors
) {
}
