package com.rentalfast.app.domain.dtos;

import com.rentalfast.app.domain.models.RentTemplate;
import com.rentalfast.app.domain.models.Rental;

public record RentalAndRentTemplate(RentTemplate rental, Rental rentalRent) { }
