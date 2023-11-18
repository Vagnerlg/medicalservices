package vagnerlg.com.github.medicalservices.presentation.http.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vagnerlg.com.github.medicalservices.presentation.http.response.exception.NotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerResponse {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> notFoundHandler(NotFoundException ex) {
        Map<String,String> map = new HashMap<>();
        map.put("errors", ex.getMessage());
        return map;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> notFoundHandler(MethodArgumentNotValidException ex) {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((FieldError error) -> {
            String field = error.getField();
            String msg = error.getDefaultMessage();

            errors.put(field, msg);
        });

        return errors;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> notReadable(HttpMessageNotReadableException ex) {
        Map<String,String> map = new HashMap<>();
        map.put("errors", ex.getMessage());
        return map;
    }
}
