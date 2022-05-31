package com.shop.demo.service;

import java.util.Optional;

import com.shop.demo.model.OrderDetail;
import com.shop.demo.repo.OrderDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailManager {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public Optional<OrderDetail> findById(Long id) {
        return orderDetailRepository.findById(id);
    }

    public Iterable<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    public void deleteById(Long id) {
        orderDetailRepository.deleteById(id);
    }
}
