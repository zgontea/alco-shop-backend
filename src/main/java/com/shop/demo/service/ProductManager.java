package com.shop.demo.service;

import java.util.List;
import java.util.Optional;

import com.shop.demo.model.Product;
import com.shop.demo.repo.ProductRepository;
import com.shop.demo.wrapper.ProductPageWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class ProductManager {
    
    @Autowired
    private ProductRepository productRepository;

    public ProductPageWrapper getWithOffset(int offset) {
        return ProductPageWrapper.builder().products(null).totalPages(2).build();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
