package com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.repository;

import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities.EntityRol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPARepositoryRol extends JpaRepository<EntityRol, String> {
}
