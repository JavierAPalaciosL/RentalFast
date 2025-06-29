package com.rentalfast.app.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Date dateStartRent;
    private Date dateEndRent;
    private double totalPrice;

    private Payment payment;

    //private Date goToTheOfficeDateOnlyPayWithEffective;

}
