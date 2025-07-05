package com.rentalfast.app.infrastructure.adapters;

import com.rentalfast.app.domain.exceptions.EmailNull;
import com.rentalfast.app.domain.exceptions.EmailUserWrong;
import com.rentalfast.app.domain.exceptions.RolNotFound;
import com.rentalfast.app.domain.models.Rol;
import com.rentalfast.app.domain.models.User;
import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.adapters.UserPostgresAdapter;
import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.repository.JPARepositoryRol;
import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.repository.JPARepositoryUsers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class UserPosgresAdapterTest {

    @Mock
    private JPARepositoryUsers jpaRepositoryUsers;

    @Mock
    private JPARepositoryRol jpaRepositoryRol;

    @InjectMocks
    private UserPostgresAdapter userPostgresAdapter;

    @Test
    @DisplayName("User with rol null")
    public void saveUserWithRolNull(){
        User user = generateUser("javi.261280@gmail.com", "123", "james", "james", "bob", null, "222");

        Assertions.assertThrows(
                RolNotFound.class,
                () -> userPostgresAdapter.saveUser(user),
                "Role is null"
        );

    }

    @Test
    @DisplayName("Incorrect Rol")
    public void saveUserWithIncorrectRol(){

        User user = generateUser("javi.261280@gmail.com", "123", "james", "james", "bob", new Rol("OTHERROL",new Date()), "");

        BDDMockito.given(jpaRepositoryRol.getReferenceById(user.getRole().getRolName()))
                .willReturn(null);

        Assertions.assertThrows(RolNotFound.class,
                () -> userPostgresAdapter.saveUser(user),
                "Role not found");

    }

    @Test
    @DisplayName("Email wrong!")
    public void saveUserWithWrongEmail(){

        User user = generateUser("javi.261280@@gmail.com", "123", "james", "james", "bob", new Rol("ADMIN",new Date()), "");

        Assertions.assertThrows(EmailUserWrong.class,
                () -> userPostgresAdapter.saveUser(user),
                "The Email is not valid");

    }

    @Test
    @DisplayName("Email is null")
    public void saveUserWithNullEmail(){
        User user = generateUser(null, "123", "james", "james", "bob", new Rol("ADMIN",new Date()), "");

        Assertions.assertThrows(EmailNull.class,
                () -> userPostgresAdapter.saveUser(user),
                "The Email is null"
                );

    }

    private User generateUser(String email, String password, String firstName, String lastName, String username, Rol rol, String numberPhone) {
        return new User(email, password, username, firstName, lastName, rol, numberPhone, new Date());
    }
}
