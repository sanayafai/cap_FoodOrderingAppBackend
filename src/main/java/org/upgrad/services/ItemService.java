package org.upgrad.services;

import org.upgrad.models.Item;
import java.util.List;

/**
 * This ItemService interface gives the list of all the service that exist in the Item service implementation class.
 * Controller class will be calling the service methods by this interface.
 */
public interface ItemService {
  
    List<Item> getItemByPopularity(int restaurantId);

    Item getItemById(int id);
  
}
