package com.rentalfast.app.application.inputs;

import com.rentalfast.app.domain.models.User;

import java.util.List;

public interface GetUser {

    List<User> getUsers();
    User getUser(String email);

}
