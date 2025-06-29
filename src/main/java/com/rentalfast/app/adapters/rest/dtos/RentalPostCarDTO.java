package com.rentalfast.app.adapters.rest.dtos;

import com.rentalfast.app.domain.models.Payment;

import java.util.Date;

public record RentalPostCarDTO(String emailUser, Payment paymentType, String tuitionCar, Date startDate, Date endDate) {
}
