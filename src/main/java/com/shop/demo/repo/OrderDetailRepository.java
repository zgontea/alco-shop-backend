package com.shop.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop.demo.model.Order;
import com.shop.demo.model.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query("SELECT od FROM OrderDetail od WHERE od.order = ?1")
    public List<OrderDetail> getOrderDetailsByOrder(Order order);
}
