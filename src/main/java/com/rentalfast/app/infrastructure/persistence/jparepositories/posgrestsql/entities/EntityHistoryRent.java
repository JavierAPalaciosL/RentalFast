package com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities;

import com.rentalfast.app.domain.models.DeliveryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntityHistoryRent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carReference")
    private EntityCar carReference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userReference")
    private EntityUsers userReference;

    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment")
    private EntityPayment paymentMethod;

    private String totalPrice;
    private boolean isWasDelivered;

    private boolean isIntoHangar;

    @Column(name = "cardNumber", nullable = true)
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DeliveryStatus status;

}
