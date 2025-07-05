package com.rentalfast.app.adapters.rest.warnings;

import com.rentalfast.app.adapters.rest.warnings.errors.CarIsAlreadyBooked;
import com.rentalfast.app.adapters.rest.warnings.errors.GlobalMessageTemplate;
import com.rentalfast.app.adapters.rest.warnings.errors.GlobalMessageTemplateErrorsFields;
import com.rentalfast.app.adapters.rest.warnings.errors.NotValidJSON;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleException(HttpMessageNotReadableException ex){
        return ResponseEntity.badRequest().body(new NotValidJSON("Invalid JSON"));
    }

    @ExceptionHandler(CarIsAlreadyBooked.class)
    public ResponseEntity<?> handleException(CarIsAlreadyBooked ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).
                body(new GlobalMessageTemplate(
                        LocalDateTime.now(),
                        "CAR.CARISBOOKEDNOW!",
                        "The car do not can booked",
                        "Plase select another date there a user using this vehicle "+ex.getMessage(),
                        "/v1/rentals",
                        new GlobalMessageTemplateErrorsFields(
                                "startDate and endDate",
                                "there are a conflict with the rent"
                        )
                        ));
    }

}
