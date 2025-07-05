package com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.adapters;

import com.rentalfast.app.application.outputs.OutputPortUser;
import com.rentalfast.app.domain.exceptions.EmailNull;
import com.rentalfast.app.domain.exceptions.EmailUserWrong;
import com.rentalfast.app.domain.exceptions.RolNotFound;
import com.rentalfast.app.domain.models.Rol;
import com.rentalfast.app.domain.models.User;
import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities.EntityRol;
import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities.EntityUsers;
import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.repository.JPARepositoryRol;
import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.repository.JPARepositoryUsers;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public class UserPostgresAdapter implements OutputPortUser {

    private final JPARepositoryUsers jpaRepositoryUsers;
    private final JPARepositoryRol jpaRepositoryRol;

    public UserPostgresAdapter(JPARepositoryUsers jpaRepositoryUsers,  JPARepositoryRol jpaRepositoryRol) {
        this.jpaRepositoryUsers = jpaRepositoryUsers;
        this.jpaRepositoryRol = jpaRepositoryRol;
    }

    @Override
    public List<User> getUsers() {
        return this.jpaRepositoryUsers.findAll().stream().map(
                entityUsers ->
                        new User(entityUsers.getEmail(),
                                entityUsers.getPassword(),
                                entityUsers.getName(),
                                entityUsers.getFirstName(),
                                entityUsers.getLastName(),
                                new Rol(entityUsers.getRol().getRol(),
                                        entityUsers.getRol().getDateCreated()),
                                entityUsers.getLastName(),
                                new Date())).toList();
    }

    @Override
    public User getUser(String email) {
        EntityUsers entityUsers = this.jpaRepositoryUsers.findByEmail(email);

        return new User(entityUsers.getEmail(),
                        entityUsers.getPassword(),
                        entityUsers.getName(),
                        entityUsers.getFirstName(),
                        entityUsers.getLastName(),
                        new Rol(entityUsers.getRol().getRol(),
                                entityUsers.getRol().getDateCreated()),
                        entityUsers.getTelephone(),
                        new Date());
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public boolean deleteUser(String email) {
        EntityUsers entityUsers = this.jpaRepositoryUsers.findByEmail(email);

        this.jpaRepositoryUsers.delete(entityUsers);

        return entityUsers != null;
    }

    @Override
    public User saveUser(User user) {

        validateUser(user);

        EntityRol entityRol = this.jpaRepositoryRol.getReferenceById(user.getRole().getRolName());

        if(entityRol == null){
            throw new RolNotFound("Rol not found");
        }

        EntityUsers entityUsers = this.jpaRepositoryUsers.save(
                new EntityUsers(
                        user.getEmail(),
                        user.getPassword(),
                        user.getNumberPhone(),
                        user.getName(),
                        user.getFirstName(),
                        user.getLastName(),
                        entityRol,
                        user.getRegisteredAt()
                ));

        return new User(entityUsers.getEmail(),
                entityUsers.getPassword(),
                entityUsers.getName(),
                entityUsers.getFirstName(),
                entityUsers.getLastName(),
                new Rol(entityUsers.getRol().getRol(),
                        entityUsers.getRol().getDateCreated()),
                entityUsers.getTelephone(),
                entityUsers.getRegisteredAt());

    }

    private void validateUser(User user) {
        if(user.getEmail() == null){
            throw new EmailNull("Email is null");
        }
        if(user.getEmail() != null){
            EmailValidator emailValidator = EmailValidator.getInstance();
            if(!emailValidator.isValid(user.getEmail())){
                throw new EmailUserWrong("Email address is invalid");
            }
        }
        if(user.getRole() == null){
            throw new RolNotFound("Role is null");
        }
    }

}
