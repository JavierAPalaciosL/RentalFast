package com.rentalfast.app.application.outputs;

import com.rentalfast.app.domain.models.Rol;

public interface OutputPortRol {

    Rol saveRol(Rol rol);
    Rol findRolById(String rolId);


}
