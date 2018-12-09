package org.upgrad.services;

import org.upgrad.models.Coupon;
import org.upgrad.models.Order;
import org.upgrad.requestResponseEntity.ItemQuantity;

import java.util.ArrayList;
import java.util.List;

/**
 * This OrderService interface gives the list of all the service that exist in the order service implementation class.
 * Controller class will be calling the service methods by this interface.
 *
 * @author Chandra Prakash Tekam
 */
public interface OrderService {

    Coupon getCoupon(String couponName);

    List<Order> getOrdersByUser(Integer userId);

    Integer addOrderWithPermAddress(Integer addressId, Integer paymentId, Integer userId, ArrayList<ItemQuantity> itemQuantities,
                                    double bill, Integer couponId, double discount);

    Integer addOrder(String flatBuilNo, String locality, String city, String zipcode, int stateId, String type, int paymentId,
                     Integer userId, List<ItemQuantity> itemQuantities, double bill, Integer couponId, double discount);
}
