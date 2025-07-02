package com.rentalfast.app.application.outputs;

import com.rentalfast.app.domain.dtos.RentalAndRentTemplate;
import com.rentalfast.app.domain.models.RentTemplate;
import com.rentalfast.app.domain.models.Rental;
import com.rentalfast.app.domain.models.Ticket;

import java.time.LocalDateTime;
import java.util.Date;

public interface OutputRentAdapter {

    boolean iCanUseCar(String tuition, LocalDateTime date, LocalDateTime dateEnd);
    Ticket saveRent(Rental rental, double totalPrice);


}
