package com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.repository;

import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities.EntityHistoryRepairAndMaintance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPARepositoryHistoryAndMaintanceRepository extends JpaRepository<EntityHistoryRepairAndMaintance, Integer> {
}
