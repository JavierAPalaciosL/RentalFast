package com.rentalfast.app.adapters.rest;

import com.rentalfast.app.adapters.rest.dtos.RentalPostCarWithPayEffectiveAndDebitCardDTO;
import com.rentalfast.app.adapters.rest.warnings.errors.CarIsAlreadyBooked;
import com.rentalfast.app.adapters.rest.warnings.errors.CarPaymentWithCard;
import com.rentalfast.app.adapters.rest.warnings.errors.CarPaymentWithCash;
import com.rentalfast.app.application.usecases.UseCaseCRUDVehicle;
import com.rentalfast.app.application.usecases.UseCaseRentACar;
import com.rentalfast.app.domain.models.Car;
import com.rentalfast.app.domain.models.Payment;
import com.rentalfast.app.domain.models.Ticket;
import com.rentalfast.app.domain.models.TimeToYearsMonthsWeeksDaysAndHours;
import com.rentalfast.app.domain.utils.DateUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("v1/rentals")
public class RentalREST {

    private final UseCaseRentACar useCaseRentACar;
    private final UseCaseCRUDVehicle useCaseCRUDVehicle;

    public RentalREST(UseCaseRentACar useCaseRentACar,  UseCaseCRUDVehicle useCaseCRUDVehicle) {
        this.useCaseRentACar = useCaseRentACar;
        this.useCaseCRUDVehicle = useCaseCRUDVehicle;
    }

    @PostMapping
    public ResponseEntity<?> addRental(@RequestBody RentalPostCarWithPayEffectiveAndDebitCardDTO rental){

        boolean iCanUseCar = useCaseRentACar.iCanUseCar(rental.tuitionCar(), rental.startDate(), rental.endDate());
        if(!iCanUseCar){
            throw new CarIsAlreadyBooked("Car is already booked");
        }else if(rental.paymentType().equals(Payment.CARD) && rental.cardNumber() == null){
            throw new CarPaymentWithCard("I need a number card");
        }else if(rental.paymentType().equals(Payment.CASH) && rental.cardNumber() != null){
            throw new CarPaymentWithCash("I dont need a number card because is a pay with effective");
        }

        Car car = this.useCaseCRUDVehicle.getVehicle(rental.tuitionCar());

        TimeToYearsMonthsWeeksDaysAndHours timeToYearsMonthsWeeksDaysAndHours = DateUtils.breakdownInterval(rental.startDate(), rental.endDate());

        double total =
                        (timeToYearsMonthsWeeksDaysAndHours.getYears() * car.getPricePerYear()) +
                        (timeToYearsMonthsWeeksDaysAndHours.getMonths() * car.getPricePerMonth()) +
                        (timeToYearsMonthsWeeksDaysAndHours.getWeeks() * car.getPricePerWeek()) +
                        (timeToYearsMonthsWeeksDaysAndHours.getDays() * car.getPricePerDay()) +
                        (timeToYearsMonthsWeeksDaysAndHours.getHours() * car.getPricePerHour());

        Ticket ticket = null;
        if(rental.paymentType().equals(Payment.CASH)){
            ticket = this.useCaseRentACar.payWithCash(rental, total);
        }else if(rental.paymentType().equals(Payment.CARD)){
            ticket = this.useCaseRentACar.payWithDebit(rental, total);
        }

        return ResponseEntity.ok().body(ticket);
    }

    @GetMapping
    public ResponseEntity<?> getRentals(@RequestParam("pageNumber") int page,  @RequestParam("pageSize") int pageSize){
        return ResponseEntity.ok(this.useCaseRentACar.getAllRents(PageRequest.of(page, pageSize)));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getRental(@RequestParam String q){
        return null;
    }

    @GetMapping("/{rental-id}/cars")
    public ResponseEntity<?> getRentalCars(@PathVariable String rentalId){
        return null;
    }

}
