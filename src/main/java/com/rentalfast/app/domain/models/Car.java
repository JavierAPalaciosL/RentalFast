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
    private String hotPrice;
    private String imageSrc;

    private double pricePerHour;
    private double pricePerDay;
    private double pricePerWeek;
    private double pricePerMonth;
    private double pricePerYear;

    private String brand;

    private String engineType;

    private String bodyType;

    private String fuelType;

    private String gearBoxType;

    private String hp;

    private String torqueLbft;

    private String cylinders;

    private String driveTrain;

    private String seats;

    private String doors;

    private String height;

    private String lenght;

    private String width;

    private String wheelBase;

    private String airAConditioner;

}
