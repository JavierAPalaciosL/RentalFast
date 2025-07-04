package com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class EntityCar {

    @Id
    private String tuition;
    private String nameCar;
    private String carColor;
    private String descriptioCar;
    private Date createdDate;
    private String imageSrc;
    private String hotPrice;

    @Column(name = "pricePerHour", nullable = false, columnDefinition = "DOUBLE PRECISION DEFAULT 0.0")
    private double pricePerHour;

    @Column(name = "pricePerDay", nullable = false, columnDefinition = "DOUBLE PRECISION DEFAULT 0.0")
    private double pricePerDay;

    @Column(name = "pricePerWeek", nullable = false, columnDefinition = "DOUBLE PRECISION DEFAULT 0.0")
    private double pricePerWeek;

    @Column(name = "pricePerMonth", nullable = false, columnDefinition = "DOUBLE PRECISION DEFAULT 0.0")
    private double pricePerMonth;

    @Column(name = "pricePerYear", nullable = false, columnDefinition = "DOUBLE PRECISION DEFAULT 0.0")
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
