package com.rentalfast.app.application.inputs;

import com.rentalfast.app.domain.models.User;

public interface DeleteUser {

    boolean deleteUser(String email);
    User deleteUserWithEmail(String email);

}
