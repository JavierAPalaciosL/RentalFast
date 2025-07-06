package com.rentalfast.app.adapters.rest;

import com.rentalfast.app.adapters.rest.dtos.CreateUserDTO;
import com.rentalfast.app.application.usecases.UseCaseCRUDUser;
import com.rentalfast.app.domain.models.Rol;
import com.rentalfast.app.domain.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("v1/users")
public class UserREST {

    private final UseCaseCRUDUser useCaseCRUDUser;

    public UserREST(UseCaseCRUDUser useCaseCRUDUser) {
        this.useCaseCRUDUser = useCaseCRUDUser;
    }

    @PostMapping
    public User createUser(@RequestBody CreateUserDTO user){
        return this.useCaseCRUDUser.createUser(
                new User(user.email(), user.password(), user.name(), user.firstName(), user.lastName(), new Rol("USER", new Date()),user.phone() ,new Date())
        );
    }

    @GetMapping("/{email}")
    public User getUser(@PathVariable String email){
        return this.useCaseCRUDUser.getUser(email);
    }

    @GetMapping
    public List<User> findAll(){
        return this.useCaseCRUDUser.getUsers();
    }

    @PatchMapping("/{email}/roles")
    public ResponseEntity<List<?>> updateRoles(@PathVariable("email") String email, @RequestBody Role role){
        return null;
    }

    @GetMapping("/{email}/rents")
    public ResponseEntity<?> getRents(@PathVariable("email") String email){



        return null;
    }

}
