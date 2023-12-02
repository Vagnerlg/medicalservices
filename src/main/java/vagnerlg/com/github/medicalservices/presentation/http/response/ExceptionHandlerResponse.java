package vagnerlg.com.github.medicalservices.presentation.http.response;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vagnerlg.com.github.medicalservices.presentation.http.response.errors.Errors;
import vagnerlg.com.github.medicalservices.presentation.http.response.exception.NotFoundException;
import vagnerlg.com.github.medicalservices.presentation.http.response.errors.Error;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerResponse {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ApiResponse(responseCode = "404", content = {
            @Content(mediaType = "application/json")
    })
    public Error notFoundHandler(NotFoundException ex) {
        return new Error("Not Found", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", content = {
            @Content(mediaType = "application/json")
    })
    public Errors notFoundHandler(MethodArgumentNotValidException ex) {
        List<Error> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach((FieldError error) -> {
            String field = error.getField();
            String msg = error.getDefaultMessage();

            errors.add(new Error(field, msg));
        });

        return new Errors(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "500", content = {
            @Content(mediaType = "application/json")
    })
    public Map<String, String> notReadable(HttpMessageNotReadableException ex) {
        Map<String,String> map = new HashMap<>();
        map.put("errors", ex.getMessage());
        return map;
    }
}
