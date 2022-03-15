package com.example.demo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "category_name",unique = true, nullable = false)
    private String categoryName;

    @Column(name = "description",nullable = true)
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    private Set<Product> products = new HashSet<>();

    public Category(String categoryName, String description, Set<Product> products)
    {
        this.categoryName = categoryName;
        this.description = description;
        this.products = products;
    }

    public Category()
    {}

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Set<Product> getProducts()
    {
        return products;
    }

    public void setProducts(Set<Product> products)
    {
        this.products = products;
    }

    public void setId(Long id) { this.id = id; }

    public Long getId()
    {
        return id;
    }
}
