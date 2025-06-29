package com.rentalfast.app.application.inputs;

import com.rentalfast.app.domain.models.Car;
import com.rentalfast.app.domain.models.User;

public interface PayWithEffectiveDelivery {

    boolean deliveryCar(Car car, User user);
    boolean generateReport(Car car, User user);

}
