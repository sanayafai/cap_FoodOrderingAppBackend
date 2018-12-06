package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.upgrad.models.Order;
import org.upgrad.requestResponseEntity.ItemQuantity;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM orders WHERE user_id = ?1 ORDER BY date DESC")
    List<Order> findOrdersByUser(Integer userId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT into orders (bill, coupon_id, discount," +
            " \"date\", payment_id, user_id, address_id)\n" +
            "VALUES(?1, ?2, ?3, now(), ?4, ?5, ?6)")
    Integer saveOrder(double bill, Integer couponId, double discount, Integer paymentId, Integer userId, Integer addressId);


}
