package com.rentalfast.app.adapters.rest.warnings.errors;

import org.springframework.http.HttpStatus;

public class CarDontExists extends RuntimeException {
    public static int NOT_FOUND = HttpStatus.NOT_FOUND.value();

    public CarDontExists(String message) {
      super(message);
    }

}
