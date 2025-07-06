package com.rentalfast.app.adapters.rest.warnings.errors;

import org.springframework.http.HttpStatus;

public class CarPaymentWithCash extends RuntimeException {
  public static final int UnprocessableEntity = HttpStatus.UNPROCESSABLE_ENTITY.value();

  public CarPaymentWithCash(String message) {
        super(message);
    }
}
