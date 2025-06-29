package com.rentalfast.app.adapters.rest.dtos;

public record CreateUserDTO(String email, String password, String name, String firstName,
                            String lastName, String phone) {
}
