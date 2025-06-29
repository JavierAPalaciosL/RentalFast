package com.rentalfast.app.application.outputs;


import com.rentalfast.app.domain.models.Car;

import java.util.List;

public interface OutputPortCar {

    Car saveACar(Car newCar);
    Car findACarByTuition(String tuition);
    List<Car> findAllCars();


}
