package com.rentalfast.app.domain.exceptions;

public class EmailUserWrong extends RuntimeException {

    public EmailUserWrong(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Email wrong";
    }

}
