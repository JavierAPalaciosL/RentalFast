package com.rentalfast.app.domain.dtos;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatorMapsDTO<T, U> {
    private Map<T, U> elements;
    private boolean hasNext;
}
