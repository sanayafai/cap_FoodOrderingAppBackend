package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Category;
import org.upgrad.models.Restaurant;
import org.upgrad.repositories.RestaurantRepository;
import org.upgrad.requestResponseEntity.RestaurantResponse;
import org.upgrad.requestResponseEntity.RestaurantResponseCategorySet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This RestaurantServiceImpl interface implementation gives the list of all the service methods implementation
 * * Controller class will be calling the service methods by this class.
 *
 * @author Chnadra Prakash Tekam
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    private List<RestaurantResponse> restaurantResponseList;
    private List<Restaurant> restaurants;

    @Override
    public List<RestaurantResponse> getAllRestaurant() {
        restaurants = restaurantRepository.findAllRestaurant();
        this.getRestaurantResponseList(restaurants);
        return restaurantResponseList;
    }

    @Override
    public List<RestaurantResponse> getRestaurantByName(String kcf) {
        restaurants = restaurantRepository.findRestaurantByName(kcf);
        this.getRestaurantResponseList(restaurants);
        return restaurantResponseList;
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


    private void getRestaurantResponseList(List<Restaurant> restaurants) {
        restaurantResponseList = new ArrayList<>();
        restaurants.forEach(restaurant -> {
            StringBuilder categories = new StringBuilder();
            int count = 0;
            restaurant.getCategories().sort(Comparator.comparing(Category::getCategoryName));
            for (Category category :
                    restaurant.getCategories()) {
                if (count++ < restaurant.getCategories().size() - 1) {
                    categories.append(category.getCategoryName()).append(", ");
                } else {
                    categories.append(category.getCategoryName());
                }
            }

            RestaurantResponse res = new RestaurantResponse(restaurant.getId(), restaurant.getRestaurantName(),
                    restaurant.getPhotoUrl(), restaurant.getUserRating(), restaurant.getAvgPrice(),
                    restaurant.getNumberUsersRated(), restaurant.getAddress(), categories.toString());
            restaurantResponseList.add(res);
        });
    }
}
