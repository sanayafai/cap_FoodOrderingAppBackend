package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.upgrad.services.AddressService;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    /*
     * This endpoint is to get all states.
     */

    @GetMapping("/states")
    @CrossOrigin
    public ResponseEntity<?> allStates() {
        return new ResponseEntity<>(addressService.getAllStates(), HttpStatus.OK);
    }

}
