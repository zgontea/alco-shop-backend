package com.shop.demo.model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shopping_cart")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
public class ShoppingCart
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_cart_id", unique = true, nullable = false)
    private Long id;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "shopping_cart_id", nullable = false)
    private List<Product> products;

}
