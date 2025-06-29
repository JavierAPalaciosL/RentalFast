package com.rentalfast.app.adapters.rest;

import com.rentalfast.app.adapters.rest.dtos.RentalPostCarDTO;
import com.rentalfast.app.application.usecases.UseCaseRentACar;
import com.rentalfast.app.domain.models.Payment;
import com.rentalfast.app.domain.models.TimeToYearsMonthsWeeksDaysAndHours;
import com.rentalfast.app.domain.utils.DateUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rentals")
public class RentalREST {

    private final UseCaseRentACar useCaseRentACar;

    public RentalREST(UseCaseRentACar useCaseRentACar) {
        this.useCaseRentACar = useCaseRentACar;
    }

    @PostMapping
    public ResponseEntity<?> addRental(@RequestBody RentalPostCarDTO rental){

        boolean iCanUseCar = useCaseRentACar.iCanUseCar(rental.tuitionCar(), rental.startDate(), rental.endDate());

        if(!iCanUseCar){
            return ResponseEntity.badRequest().build();
        }



        TimeToYearsMonthsWeeksDaysAndHours timeToYearsMonthsWeeksDaysAndHours = DateUtils.breakdownInterval(rental.startDate(), rental.endDate());
        //double total = (timeToYearsMonthsWeeksDaysAndHours.getYears() * )

        return ResponseEntity.ok().body(null);
    }

            /*if(!iCanUseCar){
            return ResponseEntity.badRequest().build();
        }


        if(rental.paymentType().equals(Payment.CASH)){

            //this.useCaseRentACar.

        }else if(rental.paymentType().equals(Payment.CARD)){



        }*/


    @GetMapping
    public ResponseEntity<?> getRentals(){
        return null;
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
