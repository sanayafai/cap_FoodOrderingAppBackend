package org.upgrad.services;

import org.upgrad.models.Category;

/**
 * This CategoryService interface gives the list of all the service that exist in the category service implementation
 * class.  Controller class will be calling the service methods by this interface.
 */
public interface CategoryService {

    Iterable<Category> getAllCategories();

    Category getCategory(String categoryName);

    Integer getCategoryCount(String categoryName);

}
