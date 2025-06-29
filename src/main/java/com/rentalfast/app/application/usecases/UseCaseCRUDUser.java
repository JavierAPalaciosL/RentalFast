package com.rentalfast.app.application.usecases;

import com.rentalfast.app.application.inputs.CreateUser;
import com.rentalfast.app.application.inputs.DeleteUser;
import com.rentalfast.app.application.inputs.GetUser;
import com.rentalfast.app.application.inputs.UpdateUser;
import com.rentalfast.app.application.outputs.OutputPortUser;
import com.rentalfast.app.domain.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UseCaseCRUDUser implements GetUser, UpdateUser, DeleteUser, CreateUser {

    private final OutputPortUser outputPortUser;

    public UseCaseCRUDUser(OutputPortUser outputPortUser) {
        this.outputPortUser = outputPortUser;
    }

    @Override
    public User createUser(User user) {
        return this.outputPortUser.saveUser(user);
    }

    @Override
    public boolean deleteUser(String email) {
        return false;
    }

    @Override
    public User deleteUserWithEmail(String email) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return this.outputPortUser.getUsers();
    }

    @Override
    public User getUser(String email) {
        return this.outputPortUser.getUser(email);
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public User deleteUser(User user) {
        return null;
    }
}
