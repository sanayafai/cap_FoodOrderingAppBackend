package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.upgrad.models.Coupon;
import org.upgrad.models.Order;
import org.upgrad.repositories.CouponRepository;
import org.upgrad.requestResponseEntity.ItemQuantity;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public Coupon getCoupon(String couponName) {
        return couponRepository.findCouponByName(couponName);
    }

    @Override
    public List<Order> getOrdersByUser(Integer userId) {
        return null;
    }

    @Override
    public Integer addOrderWithPermAddress(Integer itemId, Integer quantity, Integer userId, ArrayList<ItemQuantity> itemQuantities, double bill, Integer couponId, double v1) {
        return null;
    }
}
