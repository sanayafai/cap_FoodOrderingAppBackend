package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Address;
import org.upgrad.models.Category;
import org.upgrad.models.Restaurant;
import org.upgrad.repositories.RestaurantRepository;
import org.upgrad.requestResponseEntity.RestaurantResponse;
import org.upgrad.requestResponseEntity.RestaurantResponseCategorySet;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<RestaurantResponse> getAllRestaurant() {
        List<RestaurantResponse> restaurantResponseList = new ArrayList<>();
        List<Restaurant> restaurants = restaurantRepository.findAllRestaurant();

        restaurants.forEach(restaurant -> {
            String categories = "";
            int count = 0;
            restaurant.getCategories().sort((a, b) -> a.getCategoryName().compareTo(b.getCategoryName()));
            for (Category category :
                    restaurant.getCategories()) {
               if(count++ < restaurant.getCategories().size()-1) {
                   categories += category.getCategoryName() + ", ";
               }else{
                   categories += category.getCategoryName() + "";
               }
            }

            RestaurantResponse res = new RestaurantResponse(restaurant.getId(), restaurant.getRestaurantName(),
                    restaurant.getPhotoUrl(), restaurant.getUserRating(), restaurant.getAvgPrice(),
                    restaurant.getNumberUsersRated(), restaurant.getAddress(), categories);
            restaurantResponseList.add(res);

        });
        return restaurantResponseList;
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
