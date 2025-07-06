package com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.repository;

import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities.EntityCar;
import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities.EntityHistoryRent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface JPARepositoryCar extends JpaRepository<EntityCar, String> {

    EntityCar findByTuition(String tuition);

    Slice<EntityCar> findAllBy(Pageable pageable);

}
