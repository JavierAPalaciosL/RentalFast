package com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.adapters;

import com.rentalfast.app.application.outputs.OutputPortCar;
import com.rentalfast.app.domain.dtos.PaginatorDTO;
import com.rentalfast.app.domain.models.Car;
import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities.EntityCar;
import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.repository.JPARepositoryCar;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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
                        newCar.getPricePerYear(),
                        newCar.getBrand(),
                        newCar.getEngineType(),
                        newCar.getBodyType(),
                        newCar.getFuelType(),
                        newCar.getGearBoxType(),
                        newCar.getHp(),
                        newCar.getTorqueLbft(),
                        newCar.getCylinders(),
                        newCar.getDriveTrain(),
                        newCar.getSeats(),
                        newCar.getDoors(),
                        newCar.getHeight(),
                        newCar.getLenght(),
                        newCar.getWidth(),
                        newCar.getWheelBase(),
                        newCar.getAirAConditioner()
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
                newCar.getPricePerYear(),
                newCar.getBrand(),
                newCar.getEngineType(),
                newCar.getBodyType(),
                newCar.getFuelType(),
                newCar.getGearBoxType(),
                newCar.getHp(),
                newCar.getTorqueLbft(),
                newCar.getCylinders(),
                newCar.getDriveTrain(),
                newCar.getSeats(),
                newCar.getDoors(),
                newCar.getHeight(),
                newCar.getLenght(),
                newCar.getWidth(),
                newCar.getWheelBase(),
                newCar.getAirAConditioner()

        );
    }

    @Override
    public Car findACarByTuition(String tuition) {
        EntityCar entityCar = this.repositoryCar.findByTuition(tuition);

        return new Car(
                entityCar.getTuition(),
                entityCar.getNameCar(),
                entityCar.getCarColor(),
                entityCar.getDescriptioCar(),
                entityCar.getCreatedDate(),
                entityCar.getHotPrice(),
                entityCar.getImageSrc(),
                entityCar.getPricePerHour(),
                entityCar.getPricePerDay(),
                entityCar.getPricePerWeek(),
                entityCar.getPricePerMonth(),
                entityCar.getPricePerYear(),
                entityCar.getBrand(),
                entityCar.getEngineType(),
                entityCar.getBodyType(),
                entityCar.getFuelType(),
                entityCar.getGearBoxType(),
                entityCar.getHp(),
                entityCar.getTorqueLbft(),
                entityCar.getCylinders(),
                entityCar.getDriveTrain(),
                entityCar.getSeats(),
                entityCar.getDoors(),
                entityCar.getHeight(),
                entityCar.getLenght(),
                entityCar.getWidth(),
                entityCar.getWheelBase(),
                entityCar.getAirAConditioner());
    }

    @Override
    public List<Car> findAllCars() {
        return this.repositoryCar.findAll().stream().map(entityCar ->
                new Car(
                        entityCar.getTuition(),
                        entityCar.getNameCar(),
                        entityCar.getCarColor(),
                        entityCar.getDescriptioCar(),
                        entityCar.getCreatedDate(),
                        entityCar.getHotPrice(),
                        entityCar.getImageSrc(),
                        entityCar.getPricePerHour(),
                        entityCar.getPricePerDay(),
                        entityCar.getPricePerWeek(),
                        entityCar.getPricePerMonth(),
                        entityCar.getPricePerYear(),
                        entityCar.getBrand(),
                        entityCar.getEngineType(),
                        entityCar.getBodyType(),
                        entityCar.getFuelType(),
                        entityCar.getGearBoxType(),
                        entityCar.getHp(),
                        entityCar.getTorqueLbft(),
                        entityCar.getCylinders(),
                        entityCar.getDriveTrain(),
                        entityCar.getSeats(),
                        entityCar.getDoors(),
                        entityCar.getHeight(),
                        entityCar.getLenght(),
                        entityCar.getWidth(),
                        entityCar.getWheelBase(),
                        entityCar.getAirAConditioner())).toList();
    }

    @Override
    public PaginatorDTO findAllBy(Pageable pageable) {

        Slice<EntityCar> sliceList = this.repositoryCar.findAllBy(pageable);

        List<Car> listCars = sliceList.stream().map(entityCar ->
                new Car(
                        entityCar.getTuition(),
                        entityCar.getNameCar(),
                        entityCar.getCarColor(),
                        entityCar.getDescriptioCar(),
                        entityCar.getCreatedDate(),
                        entityCar.getHotPrice(),
                        entityCar.getImageSrc(),
                        entityCar.getPricePerHour(),
                        entityCar.getPricePerDay(),
                        entityCar.getPricePerWeek(),
                        entityCar.getPricePerMonth(),
                        entityCar.getPricePerYear(),
                        entityCar.getBrand(),
                        entityCar.getEngineType(),
                        entityCar.getBodyType(),
                        entityCar.getFuelType(),
                        entityCar.getGearBoxType(),
                        entityCar.getHp(),
                        entityCar.getTorqueLbft(),
                        entityCar.getCylinders(),
                        entityCar.getDriveTrain(),
                        entityCar.getSeats(),
                        entityCar.getDoors(),
                        entityCar.getHeight(),
                        entityCar.getLenght(),
                        entityCar.getWidth(),
                        entityCar.getWheelBase(),
                        entityCar.getAirAConditioner()
                )).toList();


        return new PaginatorDTO<Car>(listCars, sliceList.hasNext());
    }

    @Override
    public boolean carExists(String tuition) {
        return this.repositoryCar.findByTuition(tuition) != null;
    }

}
