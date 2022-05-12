package com.shop.demo.service;

import java.util.Optional;

import com.shop.demo.model.Product;
import com.shop.demo.repo.CategoryRepository;
import com.shop.demo.repo.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductManager {
    private final ProductRepository productRepository;
    // private final CategoryRepository categoryRepository;

    @Autowired
    public ProductManager(ProductRepository productRepository, CategoryRepository categoryRepository) {
        super();
        this.productRepository = productRepository;
        // this.categoryRepository = categoryRepository;
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
