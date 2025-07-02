package com.rentalfast.app.application.inputs;

import com.rentalfast.app.adapters.rest.dtos.RentalPostCarWithPayEffectiveAndDebitCardDTO;
import com.rentalfast.app.domain.models.Ticket;

public interface PayWithCashAndEffective {
    Ticket payWithCash(RentalPostCarWithPayEffectiveAndDebitCardDTO rentalPostCarWithPayEffectiveAndDebitCardDTO, double totalPrice);
    Ticket payWithDebit(RentalPostCarWithPayEffectiveAndDebitCardDTO rentalPostCarWithPayEffectiveAndDebitCardDTO, double totalPrice);
}
