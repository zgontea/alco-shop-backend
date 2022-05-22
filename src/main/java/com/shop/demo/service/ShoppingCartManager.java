package com.shop.demo.service;

import java.util.Optional;

import com.shop.demo.model.ShoppingCart;
import com.shop.demo.repo.ShoppingCartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartManager {

    @Autowired
    private  ShoppingCartRepository shoppingCartRepository;

    public Optional<ShoppingCart> findById(Long id) {
        return shoppingCartRepository.findById(id);
    }

    public Iterable<ShoppingCart> findAll() {
        return shoppingCartRepository.findAll();
    }

    public ShoppingCart save(ShoppingCart cart) {
        return shoppingCartRepository.save(cart);
    }

    public void deleteById(Long id) {
        shoppingCartRepository.deleteById(id);
    }
}
