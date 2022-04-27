package com.shop.demo.service;

import java.util.Optional;

import com.shop.demo.model.Category;
import com.shop.demo.model.Product;
import com.shop.demo.repo.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class CategoryManager {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryManager(CategoryRepository categoryRepository) {
        super();
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> findByName(String name) {
        return categoryRepository.findByCategoryName(name);
    }

    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Iterable<Product> findProductsByCategoryName(String categoryName){
        Optional<Category> category = categoryRepository.findByCategoryName(categoryName);
        return category.<Iterable<Product>>map(Category::getProducts).orElse(null);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
