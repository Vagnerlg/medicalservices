package vagnerlg.com.github.medicalservices.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vagnerlg.com.github.medicalservices.exception.NotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class NotFoundResponse {

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Map<String,String> notFoundHandler(NotFoundException ex) {
        Map<String,String> map = new HashMap<>();
        map.put("errors", ex.getMessage());
        return map;
    }
}
