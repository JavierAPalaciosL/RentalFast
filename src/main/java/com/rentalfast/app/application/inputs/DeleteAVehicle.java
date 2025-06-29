package com.rentalfast.app.application.inputs;

import com.rentalfast.app.domain.models.Car;

public interface DeleteAVehicle {
    boolean deleteVehicle(Car car);
    Car deleteAVehicle(Car car);
}
