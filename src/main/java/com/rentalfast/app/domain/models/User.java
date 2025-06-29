package com.rentalfast.app.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String email;
    private String password;
    private String name;
    private String firstName;
    private String lastName;
    private Rol role;
    private String numberPhone;
    private Date registeredAt;

}
