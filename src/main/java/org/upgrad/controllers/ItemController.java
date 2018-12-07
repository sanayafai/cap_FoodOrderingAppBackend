package org.upgrad.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.upgrad.models.Item;
import org.upgrad.services.ItemService;

@RestController
@RequestMapping("/item/restaurant")
public class ItemController {
  
  @Autowired
    private ItemService itemService;

    /**
     * @param restaurantId restaurant id
     * @return restaurant details
     */
  
    @GetMapping("/{restaurantId}")
    public ResponseEntity<?> getItemById(@PathVariable int restaurantId) {
        List<Item> item =  itemService.getItemByPopularity(restaurantId);

        if (item != null && item.size() != 0) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("No Restaurant for this id!", HttpStatus.BAD_REQUEST);
        }
    }

}
