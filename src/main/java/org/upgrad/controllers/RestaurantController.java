package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.requestResponseEntity.RestaurantResponse;
import org.upgrad.requestResponseEntity.RestaurantResponseCategorySet;
import org.upgrad.services.RestaurantService;

import java.util.List;

/**
 * @author Chandra Prakash
 */
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    private List<RestaurantResponse> restaurantResponseList;

    /**
     * This endpoint  retrieves all the restaurants in order of their ratings and display the response in a JSON format
     * with the corresponding HTTP status.
     *
     * @return JSON response contains all restaurants details
     */
    @GetMapping("")
    @CrossOrigin
    public ResponseEntity<?> getAllRestaurant() {

        restaurantResponseList = restaurantService.getAllRestaurant();
        return new ResponseEntity<>(restaurantResponseList, HttpStatus.OK);
    }

    /**
     * This endpoint  retrieves all the matched restaurants by its name  and display the response in a JSON format
     * with the corresponding HTTP status.
     *
     * @param reastaurantName restaurant name for search
     * @return JSON response contains matched  restaurants details
     */
    @GetMapping("/name/{reastaurantName}")
    @CrossOrigin
    public ResponseEntity<?> getRestaurantsByName(@PathVariable("reastaurantName") String reastaurantName) {
        restaurantResponseList = restaurantService.getRestaurantByName(reastaurantName);
        if (restaurantResponseList == null)
            return new ResponseEntity<>("No Restaurant by this name!", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(restaurantResponseList, HttpStatus.OK);
    }

    /**
     * This endpoint  retrieves all the matched restaurants by category name   and display the response in a JSON format
     * with the corresponding HTTP status.
     *
     * @param categoryName category name for search
     * @return JSON response contains matched  restaurants details
     */
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<?> getResturantsByCategory(@PathVariable("categoryName") String categoryName) {
        restaurantResponseList = restaurantService.getRestaurantByCategory(categoryName);
        if (restaurantResponseList == null)
            return new ResponseEntity<>("No Restaurant under this category!", HttpStatus.NOT_FOUND);
        else {
            return new ResponseEntity<>(restaurantResponseList, HttpStatus.OK);
        }
    }

    /**
     * This endpoint  retrieves the matched restaurant by its  id    and display the response in a JSON format
     * with the corresponding HTTP status.
     *
     * @param restaurantId restaurant id  for search
     * @return JSON response contains matched  restaurant details
     */
    @GetMapping("/{restaurantId}")
    public ResponseEntity<?> getResturantsById(@PathVariable("restaurantId") int restaurantId) {
        RestaurantResponseCategorySet restaurantResponseCategorySet = restaurantService.getRestaurantDetails(restaurantId);

        if (restaurantResponseCategorySet == null)
            return new ResponseEntity<>("No Restaurant by this id!", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(restaurantResponseCategorySet, HttpStatus.OK);

    }
}


