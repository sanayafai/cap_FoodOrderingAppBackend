package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.*;

import org.upgrad.repositories.CouponRepository;
import org.upgrad.repositories.OrderRepository;
import org.upgrad.requestResponseEntity.ItemQuantity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    private CouponRepository couponRepository;

    private OrderRepository orderRepository;

    public OrderServiceImpl(CouponRepository couponRepository, OrderRepository orderRepository) {
        this.couponRepository = couponRepository;

        this.orderRepository = orderRepository;
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
        return orderRepository.saveOrder(bill, couponId, discount, paymentId, userId, addressId);
    }

    @Override
    public Integer addOrder(String flatBuilNo, String locality, String city, String zipcode,
                            int stateId, String type, int paymentId, Integer userId,
                            List<ItemQuantity> itemQuantities, double bill, Integer couponId, double discount) {
        Order order = new Order();
        order.setBill(bill);
        order.setDiscount(discount);
        Address address = new Address();
        address.setCity(city);
        address.setFlatBuilNumber(flatBuilNo);
        address.setLocality(locality);
        States states = new States();
        states.setId(stateId);
        address.setState(states);
        order.setAddress(address);
        Payment payment = new Payment();
        payment.setId(paymentId);
        order.setPayment(payment);
        User user = new User();
        user.setId(userId);
        order.setUser(user);
        order.setDate(new Date());
        //   order.setOrderItems(itemQuantities);

        Order persistOrder = orderRepository.save(order);
        return persistOrder.getId();
    }


}
