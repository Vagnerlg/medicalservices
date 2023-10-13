package vagnerlg.com.github.medicalservices.presentation.http.response.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String model, UUID id) {
        super(model + " " + id + " not found.");
    }
}
