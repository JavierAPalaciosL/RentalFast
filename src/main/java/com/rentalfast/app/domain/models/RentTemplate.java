package com.rentalfast.app.domain.models;

import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities.EntityPayment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentTemplate {

    private int id;
    private String carTuition;
    private String userEmail;
    private Date dateStart;
    private Date dateEnd;
    private EntityPayment paymentMethod;
    private String totalPrice;
    private boolean isWasDelivered;

}
