package com.rentalfast.app.application.inputs;

import java.util.Date;

public interface VehicleAvailableDates {
    boolean iCanUseCar(String tuitionCar, Date startDate, Date endDate);
}
