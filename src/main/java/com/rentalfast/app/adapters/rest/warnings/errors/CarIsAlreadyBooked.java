package com.rentalfast.app.adapters.rest.warnings.errors;

import org.springframework.http.HttpStatus;

public class CarIsAlreadyBooked extends RuntimeException {

    public static final int CONFLICT = HttpStatus.CONFLICT.value();

    public CarIsAlreadyBooked(String message) {
        super(message);
    }

}
