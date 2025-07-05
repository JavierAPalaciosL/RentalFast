package com.rentalfast.app.adapters.rest.warnings.errors;

import org.springframework.http.HttpStatus;

public class CarPaymentWithCard extends RuntimeException {

    public static final int UnprocessableEntity = HttpStatus.UNPROCESSABLE_ENTITY.value();

    public CarPaymentWithCard(String message) {
        super(message);
    }
}
