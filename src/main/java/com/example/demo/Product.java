package com.example.demo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "image",nullable = false)
    private String image;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public BigDecimal getUnitPrice()
    {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public Set<OrderDetail> getOrderDetails()
    {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails)
    {
        this.orderDetails = orderDetails;
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private Set<OrderDetail> orderDetails = new HashSet<>();

    public Product(String name, String image, BigDecimal unitPrice, Category category)
    {
        this.name = name;
        this.image = image;
        this.unitPrice = unitPrice;
        this.category = category;
    }

    public Product()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public BigDecimal getPrice()
    {
        return unitPrice;
    }

    public void setPrice(BigDecimal unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public Long getId()
    {
        return id;
    }
}
