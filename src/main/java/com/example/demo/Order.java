package com.example.demo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Table(name = "orders")
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
    private Set<OrderDetail> orderDetails = new HashSet<>();

    public Order(String shipTo, String shipEmail, String shipPhoneNo, String shipCity, String shipCountry,
            String shipPostalCode, Date orderDate, User user, Set<OrderDetail> orderDetails) {
        this.shipTo = shipTo;
        this.shipEmail = shipEmail;
        this.shipPhoneNo = shipPhoneNo;
        this.shipCity = shipCity;
        this.shipCountry = shipCountry;
        this.shipPostalCode = shipPostalCode;
        this.orderDate = orderDate;
        this.user = user;
        this.orderDetails = orderDetails;
    }

}
