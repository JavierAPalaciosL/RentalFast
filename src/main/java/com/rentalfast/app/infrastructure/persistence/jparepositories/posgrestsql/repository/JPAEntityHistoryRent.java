package com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.repository;

import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities.EntityCar;
import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities.EntityHistoryRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface JPAEntityHistoryRent extends JpaRepository<EntityHistoryRent, Integer> {

    @Query("SELECT rent FROM EntityHistoryRent rent WHERE ( ( :dateStart >= rent.dateStart and :dateStart <= rent.dateEnd ) or ( :dateEnd >= rent.dateStart and :dateEnd <= rent.dateEnd ) ) and rent.carReference = :car")
    public List<EntityHistoryRent> findByCarByIdAndDateStartAndDateEnd(
            @Param("dateStart") Date start, @Param("dateEnd") Date end, @Param("car") EntityCar tuitionCar);

}
