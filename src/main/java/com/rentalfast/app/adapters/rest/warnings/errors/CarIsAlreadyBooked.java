package com.rentalfast.app.adapters.rest.warnings.errors;

public class CarIsAlreadyBooked extends RuntimeException {
    public CarIsAlreadyBooked(String message) {
        super(message);
    }
}
