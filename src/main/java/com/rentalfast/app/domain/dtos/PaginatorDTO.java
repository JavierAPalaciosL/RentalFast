package com.rentalfast.app.domain.dtos;


import com.rentalfast.app.domain.models.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatorDTO<T> {

    private List<T> cars;
    private boolean hasNext;

}
