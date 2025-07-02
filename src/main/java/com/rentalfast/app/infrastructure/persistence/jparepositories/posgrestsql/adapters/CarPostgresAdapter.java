package com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.adapters;

import com.rentalfast.app.application.outputs.OutputPortCar;
import com.rentalfast.app.domain.models.Car;
import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities.EntityCar;
import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.repository.JPARepositoryCar;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CarPostgresAdapter implements OutputPortCar {

    private JPARepositoryCar repositoryCar;

    public  CarPostgresAdapter(JPARepositoryCar repositoryCar) {
        this.repositoryCar = repositoryCar;
    }

    @Override
    public Car saveACar(Car newCar) {
        EntityCar entityCar = this.repositoryCar.save(
                new EntityCar(
                        newCar.getTuition(),
                        newCar.getNameCar(),
                        newCar.getColorCar(),
                        newCar.getDescriptionCar(),
                        newCar.getFabricationCar(),
                        newCar.getImageSrc(),
                        newCar.getHotPrice(),
                        newCar.getPricePerHour(),
                        newCar.getPricePerDay(),
                        newCar.getPricePerWeek(),
                        newCar.getPricePerMonth(),
                        newCar.getPricePerYear()

                        ));
        return new Car(
                entityCar.getTuition(),
                entityCar.getNameCar(),
                entityCar.getCarColor(),
                entityCar.getDescriptioCar(),
                entityCar.getCreatedDate(),
                entityCar.getHotPrice(),
                entityCar.getImageSrc(),
                newCar.getPricePerHour(),
                newCar.getPricePerDay(),
                newCar.getPricePerWeek(),
                newCar.getPricePerMonth(),
                newCar.getPricePerYear()

        );
    }

    @Override
    public Car findACarByTuition(String tuition) {
        EntityCar entityCar = this.repositoryCar.findByTuition(tuition);
        return new Car(entityCar.getTuition(), entityCar.getNameCar(),entityCar.getCarColor(), entityCar.getDescriptioCar(), entityCar.getCreatedDate(), entityCar.getHotPrice(), entityCar.getImageSrc(), entityCar.getPricePerHour(), entityCar.getPricePerDay(), entityCar.getPricePerWeek(), entityCar.getPricePerMonth(),entityCar.getPricePerYear());
    }

    @Override
    public List<Car> findAllCars() {
        return this.repositoryCar.findAll().stream().map(entityCar -> new Car(entityCar.getTuition(), entityCar.getNameCar(),entityCar.getCarColor(), entityCar.getDescriptioCar(), entityCar.getCreatedDate(), entityCar.getHotPrice(), entityCar.getImageSrc(), entityCar.getPricePerHour(), entityCar.getPricePerDay(), entityCar.getPricePerWeek(), entityCar.getPricePerMonth(),entityCar.getPricePerYear())).toList();
    }


}
