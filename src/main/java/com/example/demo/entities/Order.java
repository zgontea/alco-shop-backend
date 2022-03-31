package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;

import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

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

    @Column(name = "ship_to", nullable = false)
    private String shipTo;

    @Column(name = "ship_email", nullable = false)
    private String shipEmail;

    @Column(name = "ship_phone_no", nullable = true)
    private String shipPhoneNo;

    @Column(name = "ship_city", nullable = false)
    private String shipCity;

    @Column(name = "ship_country", nullable = false)
    private String shipCountry;

    @Column(name = "ship_postal_code", nullable = false)
    private String shipPostalCode;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;

}
