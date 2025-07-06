package com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.adapters;

import com.rentalfast.app.application.outputs.OutputRentAdapter;
import com.rentalfast.app.domain.dtos.PaginatorMapsDTO;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public PaginatorMapsDTO<String, LinkedHashMap<String, List<Ticket>>> getAllRents(Pageable pageable) {

        Slice<EntityHistoryRent> slice = this.jpaEntityHistoryRent.getAllBy(pageable);

        Map<String, LinkedHashMap<String, List<Ticket>>> dataByUser =
                slice.stream()
                        .collect(Collectors.groupingBy(
                                // 1er nivel: email, manteniendo orden
                                e -> e.getUserReference().getEmail(),
                                LinkedHashMap::new,
                                // 2º nivel: status → lista de Tickets, manteniendo orden de aparición
                                Collectors.groupingBy(
                                        e -> {
                                            if (e.getStatus() == DeliveryStatus.PENDING) {
                                                return "PENDING";
                                            } else if (e.getStatus() == DeliveryStatus.SUCCESS) {
                                                return e.isIntoHangar() ? "SUCCESS" : "ON COURSE";
                                            } else {
                                                return "CANCELLED";
                                            }
                                        },
                                        LinkedHashMap::new,
                                        // mapea cada entidad a su Ticket y lo recopila en lista
                                        Collectors.mapping(
                                                e -> Ticket.builder()
                                                        .emailUser(e.getUserReference().getEmail())
                                                        .tuitionCar(e.getCarReference().getTuition())
                                                        .nameCar(e.getCarReference().getNameCar())
                                                        .descriptionCar(e.getCarReference().getDescriptioCar())
                                                        .dateStartRent(e.getDateStart())
                                                        .dateEndRent(e.getDateEnd())
                                                        .totalPrice(Double.parseDouble(e.getTotalPrice()))
                                                        .payment(
                                                                e.getPaymentMethod().getPayment().equals(Payment.CASH)
                                                                        ? Payment.CASH : Payment.CARD
                                                        )
                                                        .build(),
                                                Collectors.toList()
                                        )
                                )
                        ));


        return new PaginatorMapsDTO<>(dataByUser, slice.hasNext());

    }

}
