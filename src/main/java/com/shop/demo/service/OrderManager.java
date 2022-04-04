package com.shop.demo.service;

import java.util.Optional;

import com.shop.demo.model.Order;
import com.shop.demo.repo.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderManager {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderManager(OrderRepository orderRepository) {
        super();
        this.orderRepository = orderRepository;
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order save(Order category) {
        return orderRepository.save(category);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
