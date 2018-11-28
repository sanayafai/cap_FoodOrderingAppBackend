package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Restaurant;
import org.upgrad.repositories.RestaurantRepository;
import org.upgrad.requestResponseEntity.RestaurantResponse;
import org.upgrad.requestResponseEntity.RestaurantResponseCategorySet;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private  RestaurantRepository restaurantRepository;
    @Override
    public List<RestaurantResponse> getAllRestaurant() {
        //return null;
        return restaurantRepository.findAllRestaurant();
    }

    @Override
    public List<RestaurantResponse> getRestaurantByName(String kcf) {
        return null;
    }

    @Override
    public List<RestaurantResponse> getRestaurantByCategory(String indean) {
        return null;
    }

    @Override
    public RestaurantResponseCategorySet getRestaurantDetails(int i) {
        return null;
    }

    @Override
    public Restaurant updateRating(int i, int i1) {
        return null;
    }
}
