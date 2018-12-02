package org.upgrad.services;

import org.upgrad.models.Coupon;

import org.upgrad.requestResponseEntity.ItemQuantity;

import java.util.ArrayList;
import java.util.List;

public interface OrderService {

    Coupon getCoupon(String couponName);

  //  List<Order> getOrdersByUser(Integer userId);

    Integer addOrderWithPermAddress(Integer itemId, Integer quantity, Integer userId, ArrayList<ItemQuantity> itemQuantities, double bill, Integer couponId, double v1);
}
