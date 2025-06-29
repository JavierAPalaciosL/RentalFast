package com.rentalfast.app.application.inputs;

import com.rentalfast.app.domain.models.Status;

import java.util.List;

public interface CheckStatusCar {
    Status getStatus(String tuitionCar);
    List<Status> getAvailableStatuses(List<String> tuitionCars);
}
