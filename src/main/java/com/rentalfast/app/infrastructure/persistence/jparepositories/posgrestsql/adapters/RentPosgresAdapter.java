package com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.adapters;

import com.rentalfast.app.application.outputs.OutputRentAdapter;
import com.rentalfast.app.domain.dtos.RentalAndRentTemplate;
import com.rentalfast.app.domain.models.DeliveryStatus;
import com.rentalfast.app.domain.models.Payment;
import com.rentalfast.app.domain.models.Rental;
import com.rentalfast.app.domain.models.Ticket;
import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities.EntityCar;
import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities.EntityHistoryRent;
import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.repository.JPAEntityHistoryRent;
import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.repository.JPARepositoryCar;
import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.repository.JPARepositoryPayment;
import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.repository.JPARepositoryUsers;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;

@Repository
public class RentPosgresAdapter implements OutputRentAdapter {

    private JPAEntityHistoryRent jpaEntityHistoryRent;
    private JPARepositoryCar jpaRepositoryCar;
    private JPARepositoryUsers  jpaRepositoryUsers;
    private JPARepositoryPayment  jpaRepositoryPayment;

    public RentPosgresAdapter(JPAEntityHistoryRent jpaEntityHistoryRent, JPARepositoryCar jpaRepositoryCar, JPARepositoryUsers jpaRepositoryUsers, JPARepositoryPayment jpaRepositoryPayment) {
        this.jpaEntityHistoryRent = jpaEntityHistoryRent;
        this.jpaRepositoryCar = jpaRepositoryCar;
        this.jpaRepositoryUsers = jpaRepositoryUsers;
        this.jpaRepositoryPayment = jpaRepositoryPayment;
    }

    @Override
    public boolean iCanUseCar(String tuition, LocalDateTime date, LocalDateTime dateEnd) {
        EntityCar entityCar = this.jpaRepositoryCar.findByTuition(tuition);
        return this.jpaEntityHistoryRent.findConflictingRents(entityCar.getTuition(), date, dateEnd) == null;
    }

    @Override
    public Ticket saveRent(Rental rental, double totalPrice) {

        EntityHistoryRent entityHistoryRent = this.jpaEntityHistoryRent.save(

                EntityHistoryRent
                        .builder()
                        .carReference(this.jpaRepositoryCar.findByTuition(rental.getTuitionCar()))
                        .userReference(this.jpaRepositoryUsers.findByEmail(rental.getEmailUser()))
                        .dateStart(rental.getStartDate())
                        .dateEnd(rental.getEndDate()).
                        paymentMethod(this.jpaRepositoryPayment.findById(rental.getPaymentType().getValue()).get())
                        .totalPrice(String.valueOf(totalPrice))
                        .isWasDelivered(true)
                        .isIntoHangar(true)
                        .status((rental.getPaymentType() == Payment.CASH) ? DeliveryStatus.PENDING : DeliveryStatus.SUCCESS)
                        .cardNumber((rental.getPaymentType() == Payment.CARD) ? rental.getNumberCard() : null)
                        .build()
        );

        return Ticket.builder().
                emailUser(entityHistoryRent.getUserReference().getEmail()).
                tuitionCar(entityHistoryRent.getCarReference().getTuition()).
                nameCar(entityHistoryRent.getCarReference().getNameCar()).
                descriptionCar(entityHistoryRent.getCarReference().getDescriptioCar()).
                dateStartRent(entityHistoryRent.getDateStart()).
                dateEndRent(entityHistoryRent.getDateEnd()).
                totalPrice(Double.parseDouble(entityHistoryRent.getTotalPrice())).
                payment(rental.getPaymentType())
                .build();

    }

}
