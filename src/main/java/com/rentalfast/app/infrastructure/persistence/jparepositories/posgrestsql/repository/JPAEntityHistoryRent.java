package com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.repository;

import com.rentalfast.app.infrastructure.persistence.jparepositories.posgrestsql.entities.EntityHistoryRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;

public interface JPAEntityHistoryRent extends JpaRepository<EntityHistoryRent, Integer> {

        @Query("""
       SELECT e
       FROM EntityHistoryRent e
       WHERE e.carReference.tuition = :carId
         AND e.dateStart   <= :newEnd
         AND e.dateEnd     >= :newStart
         AND e.status != 'CANCELLED'
    """)
    EntityHistoryRent findConflictingRents(
            @Param("carId")    String carId,
            @Param("newStart") LocalDateTime newStart,
            @Param("newEnd")   LocalDateTime   newEnd
    );


}
