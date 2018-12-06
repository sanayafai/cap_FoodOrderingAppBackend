package org.upgrad.services;

import java.util.List;

public interface ItemService {
  
    List<Item> getItemByPopularity(int restaurantId);

    Item getItemById(int id);
  
}
