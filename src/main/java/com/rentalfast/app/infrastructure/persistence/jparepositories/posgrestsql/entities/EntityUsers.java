package com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityUsers {

    @Id
    private String email;
    private String password;
    private String telephone;

    private String name;
    private String firstName;
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol")
    private EntityRol rol;
    private Date registeredAt;

}
