package com.rentalfast.app.application.usecases;

import com.rentalfast.app.application.outputs.OutputPortCar;
import com.rentalfast.app.domain.models.Car;
import com.rentalfast.app.application.inputs.CreateANewVehicle;
import com.rentalfast.app.application.inputs.DeleteAVehicle;
import com.rentalfast.app.application.inputs.GetAVehicle;
import com.rentalfast.app.application.inputs.UpdateAVehicle;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UseCaseCRUDVehicle implements CreateANewVehicle, DeleteAVehicle, GetAVehicle, UpdateAVehicle {

    private final OutputPortCar outputPortCar;

    public UseCaseCRUDVehicle(OutputPortCar outputPortCar) {
        this.outputPortCar = outputPortCar;
    }

    @Override
    public Car createNewVehicle(Car car) {
        return this.outputPortCar.saveACar(car);
    }

    @Override
    public boolean deleteVehicle(Car car) {
        return false;
    }

    @Override
    public Car deleteAVehicle(Car car) {
        return null;
    }

    @Override
    public List<Car> getVehicles() {
        return this.outputPortCar.findAllCars();
    }

    @Override
    public Car getVehicle(String tuitionVehicle) {
        return this.outputPortCar.findACarByTuition(tuitionVehicle);
    }

    @Override
    public List<Car> getVehiclesByRange(int pageNumber, int pageSize) {
        return this.outputPortCar.findAllBy(PageRequest.of(pageNumber, pageSize)).stream().toList();
    }

    @Override
    public boolean isUpdate(Car car) {
        return false;
    }

    @Override
    public Car update(Car car) {
        return null;
    }

}
