package com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class EntityRol {

    @Id
    private String rol;
    private Date dateCreated;

}
