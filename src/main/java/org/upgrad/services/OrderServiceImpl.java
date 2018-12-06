package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Coupon;

import org.upgrad.models.Order;
import org.upgrad.repositories.AddressRepository;
import org.upgrad.repositories.CouponRepository;
import org.upgrad.repositories.OrderRepository;
import org.upgrad.requestResponseEntity.ItemQuantity;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    private CouponRepository couponRepository;

    private OrderRepository orderRepository;

    private final AddressRepository addressRepository;

    public OrderServiceImpl(CouponRepository couponRepository, OrderRepository orderRepository, AddressRepository addressRepository) {

        this.couponRepository = couponRepository;

        this.orderRepository = orderRepository;

        this.addressRepository =  addressRepository;
    }

    @Override
    public Coupon getCoupon(String couponName) {
        return couponRepository.findCouponByName(couponName);
    }

    @Override
    public List<Order> getOrdersByUser(Integer userId) {
        return orderRepository.findOrdersByUser(userId);
    }

    @Override
    public Integer addOrderWithPermAddress(Integer addressId, Integer paymentId, Integer userId, ArrayList<ItemQuantity> itemQuantities, double bill, Integer couponId, double discount) {
        return null;
    }

    @Override
    public Integer addOrder(String flatBuilNo, String locality, String city, String zipcode, int stateId, String type, int paymentId, Integer userId, List<ItemQuantity> itemQuantities, double bill, Integer couponId, double discount) {

        return null;
    }


}
