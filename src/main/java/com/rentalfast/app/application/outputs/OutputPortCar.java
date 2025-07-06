package com.rentalfast.app.application.outputs;


import com.rentalfast.app.domain.dtos.PaginatorDTO;
import com.rentalfast.app.domain.models.Car;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface OutputPortCar {

    Car saveACar(Car newCar);
    Car findACarByTuition(String tuition);
    List<Car> findAllCars();
    PaginatorDTO findAllBy(Pageable pageable);
    boolean carExists(String tuition);

}
