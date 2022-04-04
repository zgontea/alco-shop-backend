package com.shop.demo.service;

import java.util.Optional;

import com.shop.demo.model.OrderDetail;
import com.shop.demo.repo.OrderDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailManager {

    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailManager(OrderDetailRepository orderDetailRepository) {
        super();
        this.orderDetailRepository = orderDetailRepository;
    }

    public Optional<OrderDetail> findById(Long id) {
        return orderDetailRepository.findById(id);
    }

    public Iterable<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    public OrderDetail save(OrderDetail category) {
        return orderDetailRepository.save(category);
    }

    public void deleteById(Long id) {
        orderDetailRepository.deleteById(id);
    }
}
