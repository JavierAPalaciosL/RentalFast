package com.rentalfast.app.adapters.rest.dtos;

import com.rentalfast.app.domain.models.Payment;

import java.time.LocalDateTime;

public record RentalPostCarWithPayEffectiveAndDebitCardDTO(String emailUser, Payment paymentType, String cardNumber, String tuitionCar, LocalDateTime startDate, LocalDateTime endDate) {
}
