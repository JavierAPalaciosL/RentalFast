package com.rentalfast.app.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private String tuition;
    private String nameCar;
    private String colorCar;
    private String descriptionCar;
    private Date fabricationCar;
    private double hotPrice;
    private String imageSrc;

    private double pricePerHour;
    private double pricePerDay;
    private double pricePerWeek;
    private double pricePerMonth;
    private double pricePerYear;

}
