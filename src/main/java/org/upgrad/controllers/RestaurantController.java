package org.upgrad.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chandra Prakash
 */
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @GetMapping("")
    public ResponseEntity<?> getAllRestaurant(){

        return null;
    }
}
