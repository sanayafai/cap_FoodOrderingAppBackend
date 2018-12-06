package org.upgrad.services;

import org.upgrad.models.Category;

public interface CategoryService {
  
    Iterable<Category> getAllCategories();

    Category getCategory(String categoryName);

    Integer getCategoryCount(String categoryName);

}
