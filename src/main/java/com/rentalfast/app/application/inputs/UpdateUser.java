package com.rentalfast.app.application.inputs;

import com.rentalfast.app.domain.models.User;

public interface UpdateUser {
    boolean updateUser(User user);
    User deleteUser(User user);
}
