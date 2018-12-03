package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.upgrad.models.Order;
import org.upgrad.requestResponseEntity.ItemQuantity;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Query(nativeQuery = true , value = "SELECT * FROM orders WHERE user_id = ?1 ORDER BY date DESC")
    List<Order> findOrdersByUser(Integer userId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO public.orders" +
            "(bill, coupon_id, discount, \"date\", payment_id, user_id, address_id)\n" +
            "VALUES(0, 0, 0, '', 0, 0, 0);\n")
     Integer saveOrder(String flatBuilNo, String locality, String city, String zipcode, int i, String type, int i1,
                            Integer userId, List<ItemQuantity> itemQuantities, double v, Integer couponId, double v1) ;

    }
