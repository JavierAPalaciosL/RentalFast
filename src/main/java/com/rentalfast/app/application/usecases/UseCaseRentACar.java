package com.rentalfast.app.application.usecases;

import com.rentalfast.app.adapters.rest.dtos.RentalPostCarWithPayEffectiveAndDebitCardDTO;
import com.rentalfast.app.application.inputs.*;
import com.rentalfast.app.application.outputs.OutputRentAdapter;
import com.rentalfast.app.domain.dtos.PaginatorMapsDTO;
import com.rentalfast.app.domain.models.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class UseCaseRentACar implements CheckStatusCar, VehicleAvailableDates, CanIRentACar, PayWithCashAndEffective, GetAllRents {

    private OutputRentAdapter outputRentAdapter;

    public UseCaseRentACar(OutputRentAdapter outputRentAdapter) {
        this.outputRentAdapter = outputRentAdapter;
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
    public Ticket payWithCash(RentalPostCarWithPayEffectiveAndDebitCardDTO rentalPostCarWithPayEffectiveAndDebitCardDTO, double totalPrice) {
        return this.outputRentAdapter.saveRent(
                new Rental(
                        rentalPostCarWithPayEffectiveAndDebitCardDTO.emailUser(),
                        rentalPostCarWithPayEffectiveAndDebitCardDTO.paymentType(),
                        rentalPostCarWithPayEffectiveAndDebitCardDTO.tuitionCar(),
                        rentalPostCarWithPayEffectiveAndDebitCardDTO.startDate(),
                        rentalPostCarWithPayEffectiveAndDebitCardDTO.endDate(),
                        ""),
                totalPrice);
    }


    @Override
    public boolean iCanUseCar(String tuitionCar, LocalDateTime startDate, LocalDateTime endDate) {
        return this.outputRentAdapter.iCanUseCar(tuitionCar, startDate, endDate);

    }

    @Override
    public Ticket payWithDebit(RentalPostCarWithPayEffectiveAndDebitCardDTO rentalPostCarWithPayEffectiveAndDebitCardDTO, double totalPrice) {
        return this.outputRentAdapter.saveRent(
                new Rental(
                        rentalPostCarWithPayEffectiveAndDebitCardDTO.emailUser(),
                        rentalPostCarWithPayEffectiveAndDebitCardDTO.paymentType(),
                        rentalPostCarWithPayEffectiveAndDebitCardDTO.tuitionCar(),
                        rentalPostCarWithPayEffectiveAndDebitCardDTO.startDate(),
                        rentalPostCarWithPayEffectiveAndDebitCardDTO.endDate(),
                        rentalPostCarWithPayEffectiveAndDebitCardDTO.cardNumber()),
                totalPrice);
    }

    @Override
    public PaginatorMapsDTO<String, LinkedHashMap<String, List<Ticket>>> getAllRents(Pageable pageable) {
        return this.outputRentAdapter.getAllRents(pageable);
    }

}
