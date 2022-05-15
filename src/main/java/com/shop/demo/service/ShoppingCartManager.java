package com.shop.demo.service;

import com.shop.demo.model.Product;
import com.shop.demo.model.ShoppingCart;
import com.shop.demo.repo.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartManager
{
    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartManager(ShoppingCartRepository shoppingCartRepository)
    {
        super();
        this.shoppingCartRepository = shoppingCartRepository;
    }

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
