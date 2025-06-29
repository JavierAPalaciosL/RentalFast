package com.rentalfast.app.application.usecases;

import com.rentalfast.app.adapters.rest.dtos.RentalPostCarDTO;
import com.rentalfast.app.application.inputs.*;
import com.rentalfast.app.application.outputs.OutputRentAdapter;
import com.rentalfast.app.domain.models.Rental;
import com.rentalfast.app.domain.models.Status;
import com.rentalfast.app.domain.models.Ticket;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UseCaseRentACar implements CheckStatusCar, VehicleAvailableDates, CanIRentACar, EffectivePay, CreditPay {

    private OutputRentAdapter outputRentAdapter;

    public UseCaseRentACar(OutputRentAdapter outputRentAdapter) {
        this.outputRentAdapter = outputRentAdapter;
    }

    @Override
    public boolean iCanUseCar(String tuitionCar, Date startDate, Date endDate) {
        return this.outputRentAdapter.iCanUseCar(tuitionCar, startDate, endDate);
    }

    @Override
    public Status getStatus(String tuitionCar) {
        return null;
    }

    @Override
    public List<Status> getAvailableStatuses(List<String> tuitionCars) {
        return List.of();
    }

    @Override
    public boolean canRentACar(String tuitionCar) {
        return false;
    }


    @Override
    public Ticket doPayCash(RentalPostCarDTO rentalPostCarDTO) {
        return this.outputRentAdapter.saveRent(new Rental(rentalPostCarDTO.emailUser(), rentalPostCarDTO.paymentType(), rentalPostCarDTO.tuitionCar(), rentalPostCarDTO.startDate(), rentalPostCarDTO.endDate()));
    }

    @Override
    public Ticket doEffectivePay(RentalPostCarDTO rentalPostCarDTO) {
        return null;
    }
}
