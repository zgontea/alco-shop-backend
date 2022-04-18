package com.shop.demo.service;

import java.util.Optional;

import com.shop.demo.model.Category;
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

    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
