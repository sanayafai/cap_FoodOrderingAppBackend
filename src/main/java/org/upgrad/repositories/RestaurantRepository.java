package org.upgrad.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Restaurant;

/**
 * This repository interface is responsible for the interaction between the restaurant service with the restaurant
 * database
 * @author Chnadra Prakash
 */
@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

     @Query(nativeQuery = true, value = "SELECT * FROM RESTAURANT")
    Restaurant findAllRestaurant();
}
