package com.rentalfast.app.adapters.rest;

import com.rentalfast.app.application.usecases.UseCaseCRUDVehicle;
import com.rentalfast.app.domain.models.Car;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/cars")
public class CarsREST {

    private final UseCaseCRUDVehicle useCaseCRUDVehicle;

    public CarsREST(UseCaseCRUDVehicle useCaseCRUDVehicle) {
        this.useCaseCRUDVehicle = useCaseCRUDVehicle;
    }

    @PostMapping
    public ResponseEntity<?> addCar(@Valid @RequestBody Car cars){
        return ResponseEntity.ok(this.useCaseCRUDVehicle.createNewVehicle(cars));
    }

    @GetMapping("/{carId}/rentals")
    public ResponseEntity<?> getRentals(@PathVariable Integer carId){

        return null;
    }

    @GetMapping("/{carId}")
    public ResponseEntity<?> getCar(@PathVariable Integer carId){

        return null;
    }


}
