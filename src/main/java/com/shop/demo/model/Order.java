package com.shop.demo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "orders")
@Data
@SuperBuilder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "created_date", nullable = true)
    private LocalDateTime createdDate;

    @Column(name = "ship_address", nullable = true)
    private String shipAddress;

    @Column(name = "ship_city", nullable = true)
    private String shipCity;

    @Column(name = "ship_email", nullable = true)
    private String shipEmail;

    @Column(name = "ship_phone_no", nullable = true)
    private String shipPhoneNo;

    @Column(name = "ship_postal_code", nullable = true)
    private String shipPostalCode;

    @Column(name = "status", nullable = true)
    private String status;

    @Column(name = "total_price", nullable = true)
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<OrderDetail> orderDetails;
}
