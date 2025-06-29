package com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.repository;

import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities.EntityReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPARepositoryEntityReport extends JpaRepository<EntityReport, Integer> {
}
