package com.rentalfast.app.application.inputs;

import com.rentalfast.app.adapters.rest.dtos.RentalPostCarDTO;
import com.rentalfast.app.domain.models.Ticket;

public interface EffectivePay {
    Ticket doEffectivePay(RentalPostCarDTO rentalPostCarDTO);
}
