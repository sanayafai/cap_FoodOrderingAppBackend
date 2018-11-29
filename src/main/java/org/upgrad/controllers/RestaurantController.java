package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.upgrad.models.Restaurant;
import org.upgrad.requestResponseEntity.RestaurantResponse;
import org.upgrad.services.RestaurantService;

import java.util.List;

/**
 * @author Chandra Prakash
 */
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("")
    public ResponseEntity<?> getAllRestaurant(){

        List<RestaurantResponse> restaurantResponseList = restaurantService.getAllRestaurant();
        return new ResponseEntity<>(restaurantResponseList,  HttpStatus.OK);
    }
}
