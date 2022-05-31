package com.shop.demo.service;

import java.util.Optional;

import com.shop.demo.model.Order;
import com.shop.demo.repo.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderManager {

    @Autowired
    private OrderRepository orderRepository;

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
