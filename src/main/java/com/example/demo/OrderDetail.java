package com.example.demo;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_detail")
public class OrderDetail
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "price",nullable = false)
    private BigDecimal price;

    @Column(name = "quantity",nullable = false)
    private Integer quantity;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    //@JoinColumn(name = "order_id",nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public OrderDetail(BigDecimal price, Integer quantity, Order order, Product product)
    {
        this.price = price;
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }

    public OrderDetail()
    {
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }


    public void setId(Long id)
    {
        this.id = id;
    }

    @Id
    public Long getId()
    {
        return id;
    }
}
