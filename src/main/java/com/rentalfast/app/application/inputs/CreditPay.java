package com.rentalfast.app.application.inputs;

import com.rentalfast.app.adapters.rest.dtos.RentalPostCarDTO;
import com.rentalfast.app.domain.models.Ticket;

public interface CreditPay {

    Ticket doPayCash(RentalPostCarDTO rentalPostCarDTO);

}
