package org.upgrad.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Restaurant;

import java.util.List;

/**
 * This repository interface is responsible for the interaction between the restaurant service with the restaurant
 * database
 *
 * @author Chnadra Prakash
 */
@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM restaurant ORDER BY user_rating DESC")
    List<Restaurant> findAllRestaurant();

    @Query(nativeQuery = true, value = "SELECT * FROM RESTAURANT WHERE restaurant_name ILIKE %?1% ORDER BY restaurant_name")
    List<Restaurant> findRestaurantByName(String restaurantName);
}
