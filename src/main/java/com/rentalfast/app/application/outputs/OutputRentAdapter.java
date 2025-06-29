package com.rentalfast.app.application.outputs;

import com.rentalfast.app.domain.models.Rental;
import com.rentalfast.app.domain.models.Ticket;

import java.util.Date;

public interface OutputRentAdapter {

    boolean iCanUseCar(String tuition, Date date, Date dateEnd);
    Ticket saveRent(Rental rental);


}
