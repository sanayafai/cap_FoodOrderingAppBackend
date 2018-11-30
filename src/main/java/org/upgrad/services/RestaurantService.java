package org.upgrad.services;

import org.upgrad.models.Restaurant;
import org.upgrad.requestResponseEntity.RestaurantResponse;
import org.upgrad.requestResponseEntity.RestaurantResponseCategorySet;

import java.util.List;

/**
 * This RestaurantService interface gives the list of all the service that exist in the restaurant service
 * implementation class.
 * Controller class will be calling the service methods by this interface.
 *
 * @author Chandra Prakash tekam
 */
public interface RestaurantService {

    List<RestaurantResponse> getAllRestaurant();

    List<RestaurantResponse> getRestaurantByName(String restaurantName);

    List<RestaurantResponse> getRestaurantByCategory(String categoryName);

    RestaurantResponseCategorySet getRestaurantDetails(int i);

    Restaurant updateRating(int i, int i1);
}
