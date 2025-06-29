package com.rentalfast.app.application.inputs;

import com.rentalfast.app.domain.models.Car;

public interface UpdateAVehicle {

    boolean isUpdate(Car car);
    Car update(Car car);

}
