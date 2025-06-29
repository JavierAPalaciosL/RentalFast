package com.rentalfast.app.adapters.rest.warnings;

import com.rentalfast.app.adapters.rest.warnings.errors.NotValidJSON;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleException(HttpMessageNotReadableException ex){
        return ResponseEntity.badRequest().body(new NotValidJSON("Invalid JSON"));
    }

}
