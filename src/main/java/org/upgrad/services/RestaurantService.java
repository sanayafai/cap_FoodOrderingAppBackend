package org.upgrad.services;

import org.upgrad.models.Restaurant;
import org.upgrad.requestResponseEntity.RestaurantResponse;
import org.upgrad.requestResponseEntity.RestaurantResponseCategorySet;

import java.util.List;

public interface RestaurantService {
    List<RestaurantResponse> getAllRestaurant();

    List<RestaurantResponse> getRestaurantByName(String kcf);

    List<RestaurantResponse> getRestaurantByCategory(String indean);

    RestaurantResponseCategorySet getRestaurantDetails(int i);

    Restaurant updateRating(int i, int i1);
}
