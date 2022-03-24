package com.example.demo.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", unique = true, nullable = false)
    private Long id;

    @NonNull
    @Column(name = "ship_to", nullable = false)
    private String shipTo;
    
    @NonNull
    @Column(name = "ship_email", nullable = false)
    private String shipEmail;
    
    @NonNull
    @Column(name = "ship_phone_no", nullable = true)
    private String shipPhoneNo;
    
    @NonNull
    @Column(name = "ship_city", nullable = false)
    private String shipCity;
    
    @NonNull
    @Column(name = "ship_country", nullable = false)
    private String shipCountry;

    @NonNull
    @Column(name = "ship_postal_code", nullable = false)
    private String shipPostalCode;

    @NonNull
    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;

}
