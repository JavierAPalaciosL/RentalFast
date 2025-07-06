package com.rentalfast.app.application.inputs;

import com.rentalfast.app.domain.dtos.PaginatorMapsDTO;
import com.rentalfast.app.domain.models.Ticket;
import org.springframework.data.domain.Pageable;

import java.util.LinkedHashMap;
import java.util.List;

public interface GetAllRents {

    public PaginatorMapsDTO<String, LinkedHashMap<String, List<Ticket>>> getAllRents(Pageable pageable);

}
