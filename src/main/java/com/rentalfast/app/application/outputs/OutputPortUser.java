package com.rentalfast.app.application.outputs;

import com.rentalfast.app.domain.models.User;

import java.util.List;

public interface OutputPortUser {
    List<User> getUsers();
    User getUser(String email);
    User updateUser(User user);
    boolean deleteUser(String email);
    User saveUser(User user);
}
