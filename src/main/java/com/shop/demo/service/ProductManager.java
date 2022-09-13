package com.shop.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.demo.model.Product;
import com.shop.demo.repo.ProductRepository;
import com.shop.demo.wrapper.ProductPageWrapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductManager {
    
    @Autowired
    private ProductRepository productRepository;

    public ProductPageWrapper getFiltered(int offset, int itemsPerPage, String category, String productName, BigDecimal unitPriceMin, BigDecimal unitPriceMax) {
        List<Product> products = productRepository.getFilteredProducts(offset, itemsPerPage, category, productName, unitPriceMin, unitPriceMax);
        long count = productRepository.getFilteredProductsCount(category, productName, unitPriceMin, unitPriceMax);
        return ProductPageWrapper.builder().products(products).itemsAmount(count).build();
    }

    public ProductPageWrapper getFiltered(int offset, int itemsPerPage, String productName, BigDecimal unitPriceMin, BigDecimal unitPriceMax) {
        List<Product> products = productRepository.getFilteredProducts(offset, itemsPerPage, productName, unitPriceMin, unitPriceMax);
        long count = productRepository.getFilteredProductsCount(productName, unitPriceMin, unitPriceMax);
        return ProductPageWrapper.builder().products(products).itemsAmount(count).build();
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
