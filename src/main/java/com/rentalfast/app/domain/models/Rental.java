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
public class Rental {

    private String emailUser;
    private Payment paymentType;
    private String tuitionCar;
    private Date startDate;
    private Date endDate;

}
