package com.rentalfast.app.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {

    private String emailUser;
    private String tuitionCar;
    private String nameCar;
    private String descriptionCar;
    private LocalDateTime dateStartRent;
    private LocalDateTime dateEndRent;
    private double totalPrice;

    private Payment payment;

    @JsonIgnore
    private DeliveryStatus status;

    //private Date goToTheOfficeDateOnlyPayWithEffective;

}
