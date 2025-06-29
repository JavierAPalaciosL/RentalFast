package com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.adapters;

import com.rentalfast.app.application.outputs.OutputPortRol;
import com.rentalfast.app.domain.models.Rol;
import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities.EntityRol;
import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.repository.JPARepositoryRol;
import org.springframework.stereotype.Repository;

@Repository
public class RolPostgresAdapter implements OutputPortRol {

    private final JPARepositoryRol repositoryRol;

    public RolPostgresAdapter(JPARepositoryRol repositoryRol) {
        this.repositoryRol = repositoryRol;
    }

    @Override
    public Rol saveRol(Rol rol) {
        EntityRol entityRol = this.repositoryRol.save(new EntityRol(rol.getRolName(), rol.getDateCreated()));
        return new Rol(entityRol.getRol(), entityRol.getDateCreated());
    }

    @Override
    public Rol findRolById(String rolId) {
        EntityRol entityRol = this.repositoryRol.getReferenceById(rolId);
        return new Rol(entityRol.getRol(), entityRol.getDateCreated());
    }
}
