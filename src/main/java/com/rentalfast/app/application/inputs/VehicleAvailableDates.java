package com.rentalfast.app.application.inputs;

import java.time.LocalDateTime;
import java.util.Date;

public interface VehicleAvailableDates {
    boolean iCanUseCar(String tuitionCar, LocalDateTime startDate, LocalDateTime endDate);
}
