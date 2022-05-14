package com.shop.demo.service;

import java.util.List;
import java.util.Optional;

import com.shop.demo.model.Product;
import com.shop.demo.repo.CategoryRepository;
import com.shop.demo.repo.ProductRepository;

import com.shop.demo.wrapper.ProductPageWrapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductManager {
    private final ProductRepository productRepository;
    // private final CategoryRepository categoryRepository;

    @Autowired
    public ProductManager(ProductRepository productRepository, CategoryRepository categoryRepository) {
        super();
        this.productRepository = productRepository;
        // this.categoryRepository = categoryRepository;
    }
    public List<Product> getProduct(int page)
    {
        Page<Product> page1 = productRepository.findAll(
                PageRequest.of(page, 8
                ));

        return page1.getContent();
    }

    public ProductPageWrapper getProductPage(int pageNumber) {
        val page = productRepository.findAll(
                PageRequest.of(pageNumber, 3));

        return ProductPageWrapper.builder()
                .items(page.getContent())
                .totalPages(page.getTotalPages())
                .build();
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
