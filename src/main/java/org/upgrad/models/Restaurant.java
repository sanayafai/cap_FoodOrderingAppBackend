package org.upgrad.models;

public class Restaurant {
    private Integer id;
    private String restaurantName;
    private Double userRating;

    public Restaurant() {
    }

    public Restaurant(Integer id, String restaurantName, Double userRating) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.userRating = userRating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Double getUserRating() {
        return userRating;
    }

    public void setUserRating(Double userRating) {
        this.userRating = userRating;
    }
}
