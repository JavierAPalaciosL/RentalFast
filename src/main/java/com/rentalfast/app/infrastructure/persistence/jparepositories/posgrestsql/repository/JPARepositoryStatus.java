package com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.repository;

import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPARepositoryStatus extends JpaRepository<EntityStatus, Integer> {
}
