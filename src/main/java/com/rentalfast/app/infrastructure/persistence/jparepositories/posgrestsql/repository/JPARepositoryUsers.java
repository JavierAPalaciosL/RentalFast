package com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.repository;

import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities.EntityUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPARepositoryUsers extends JpaRepository<EntityUsers, String> {
    EntityUsers findByEmail(String email);
}
