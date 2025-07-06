package com.rentalfast.app.adapters.rest.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WrapperPaginationDTO<T> {

    private List<T> content;
    private int page;
    private int size;
    private boolean hasNextPage;

}
