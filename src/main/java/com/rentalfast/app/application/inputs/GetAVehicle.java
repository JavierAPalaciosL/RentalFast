package com.rentalfast.app.application.inputs;

import com.rentalfast.app.domain.dtos.PaginatorDTO;
import com.rentalfast.app.domain.models.Car;

import java.util.List;

public interface GetAVehicle {

    List<Car> getVehicles();
    Car getVehicle(String tuitionVehicle);
    PaginatorDTO getVehiclesByRange(int pageNumber, int pageSize);
    boolean existsVehicle(String tuitionVehicle);

}
