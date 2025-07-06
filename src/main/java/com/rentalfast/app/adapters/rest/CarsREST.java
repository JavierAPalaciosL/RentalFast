package com.rentalfast.app.adapters.rest;

import com.rentalfast.app.adapters.rest.dtos.WrapperPaginationDTO;
import com.rentalfast.app.adapters.rest.warnings.errors.CarDontExists;
import com.rentalfast.app.application.usecases.UseCaseCRUDVehicle;
import com.rentalfast.app.domain.dtos.PaginatorDTO;
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

    @GetMapping
    public ResponseEntity<?> getCars(@RequestParam(required = false, name = "pageNumber") String pageNumber, @RequestParam(required = false, name = "pageSize") String pageSize){

        if(pageNumber == null || pageSize == null || !pageNumber.matches("\\d+") || !pageSize.matches("\\d+")){
            return ResponseEntity.ok(this.useCaseCRUDVehicle.getVehicles());
        }

        int pageAt = Integer.parseInt(pageNumber);
        int sizeAt = Integer.parseInt(pageSize);

        PaginatorDTO<Car> paginatorDTO = this.useCaseCRUDVehicle.getVehiclesByRange(pageAt, sizeAt);

        return ResponseEntity.ok(new WrapperPaginationDTO<>(paginatorDTO.getCars(), pageAt, sizeAt, paginatorDTO.isHasNext()));

    }

    @GetMapping("/{carId}/rentals")
    public ResponseEntity<?> getRentals(@PathVariable Integer carId){
        return null;
    }

    @GetMapping("/{carId}")
    public ResponseEntity<?> getCar(@PathVariable String carId){

        if(!this.useCaseCRUDVehicle.existsVehicle(carId)){
            throw new CarDontExists("ERROR carId does not exist");
        }

        return ResponseEntity.ok(this.useCaseCRUDVehicle.getVehicle(carId));
    }

}
