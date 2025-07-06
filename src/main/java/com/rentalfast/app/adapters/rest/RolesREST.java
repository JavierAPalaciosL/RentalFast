package com.rentalfast.app.adapters.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/roles")
public class RolesREST {

    @GetMapping
    public ResponseEntity<?> getRoles(){
        return null;
    }

    @GetMapping("/{rolId}")
    public ResponseEntity<?> getRole(@PathVariable Integer rolId){
        return null;
    }



}
