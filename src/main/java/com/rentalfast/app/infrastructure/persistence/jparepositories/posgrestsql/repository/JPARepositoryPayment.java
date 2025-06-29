package com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.repository;

import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities.EntityPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPARepositoryPayment extends JpaRepository<EntityPayment, Integer> {
}
