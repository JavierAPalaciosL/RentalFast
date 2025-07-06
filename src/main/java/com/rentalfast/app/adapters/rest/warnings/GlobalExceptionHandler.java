package com.rentalfast.app.adapters.rest.warnings;

import com.rentalfast.app.adapters.rest.warnings.errors.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleException(HttpMessageNotReadableException ex){
        return ResponseEntity.badRequest().body(new NotValidJSON("Invalid JSON"));
    }

    @ExceptionHandler(CarIsAlreadyBooked.class)
    public ResponseEntity<?> handleException(CarIsAlreadyBooked ex){
        return ResponseEntity.status(CarIsAlreadyBooked.CONFLICT).
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

    @ExceptionHandler(CarPaymentWithCard.class)
    public ResponseEntity<?> handleException(CarPaymentWithCard ex){
        return ResponseEntity.status(CarPaymentWithCard.UnprocessableEntity)
                .body(new GlobalMessageTemplate(
                        LocalDateTime.now(),
                        "PAYMENTWITHCARD I NEED A NUMBER CARD!",
                        "The car do not can booked",
                        "Plase insert a number card valid! "+ex.getMessage(),
                        "/v1/rentals",
                        new GlobalMessageTemplateErrorsFields(
                                "cardNumber",
                                "there are a conflict with number card"
                        ))
                );
    }

    @ExceptionHandler(CarPaymentWithCash.class)
    public ResponseEntity<?> handleException(CarPaymentWithCash ex){
        return ResponseEntity.status(CarPaymentWithCash.UnprocessableEntity)
                .body(new GlobalMessageTemplate(
                        LocalDateTime.now(),
                        "PAYMENTWITHCASH I DONT NEED A NUMBER CARD!",
                        "The car do not can booked",
                        "Plase insert only the date for the booked! "+ex.getMessage(),
                        "/v1/rentals",
                        new GlobalMessageTemplateErrorsFields(
                                "cardNumber",
                                "I dont want the number card because there is a pay with effective"
                        ))
                );
    }

    @ExceptionHandler(CarDontExists.class)
    public ResponseEntity<?> handleException(CarDontExists ex){
        return ResponseEntity.status(CarDontExists.NOT_FOUND)
                .body(new GlobalMessageTemplate(
                        LocalDateTime.now(),
                        "The car do not exist",
                        "Car not found tuition invalid",
                        "Plase insert a valid tuition car! "+ex.getMessage(),
                        "/v1/cars/{carId}",
                        new GlobalMessageTemplateErrorsFields(
                                "carId",
                                "Missing carId insert a valid carId, see /v1/cars o go to documentation"
                        ))
                );
    }

}
