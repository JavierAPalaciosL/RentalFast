package com.rentalfast.app.application.outputs;

import com.rentalfast.app.domain.dtos.PaginatorDTO;
import com.rentalfast.app.domain.dtos.PaginatorMapsDTO;
import com.rentalfast.app.domain.dtos.RentalAndRentTemplate;
import com.rentalfast.app.domain.models.RentTemplate;
import com.rentalfast.app.domain.models.Rental;
import com.rentalfast.app.domain.models.Ticket;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public interface OutputRentAdapter {

    boolean iCanUseCar(String tuition, LocalDateTime date, LocalDateTime dateEnd);
    Ticket saveRent(Rental rental, double totalPrice);
    PaginatorMapsDTO<String, LinkedHashMap<String, List<Ticket>>> getAllRents(Pageable pageable);


}
