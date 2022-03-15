package com.example.demo;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "ship_to",nullable = false)
    private String shipTo;

    @Column(name = "ship_email",nullable = false)
    private String shipEmail;

    private String shipPhoneNo;

    @Column(name = "ship_city",nullable = false)
    private String shipCity;

    @Column(name = "ship_country",nullable = false)
    private String shipCountry;

    @Column(name = "ship_postal_code",nullable = false)
    private String shipPostalCode;

    @Column(name = "order_date",nullable = false)
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private Set<OrderDetail> orderDetails = new HashSet<>();

    public Order(String shipTo, String shipEmail, String shipPhoneNo, String shipCity, String shipCountry, String shipPostalCode, Date orderDate, User user, Set<OrderDetail> orderDetails)
    {
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

    public Order()
    {
    }

    public Set<OrderDetail> getOrderDetails()
    {
        return orderDetails;
    }

    public void setOrderDetail(Set<OrderDetail> orderDetail)
    {
        this.orderDetails = orderDetails;
    }

    public String getShipTo()
    {
        return shipTo;
    }

    public void setShipTo(String shipTo)
    {
        this.shipTo = shipTo;
    }

    public String getShipEmail()
    {
        return shipEmail;
    }

    public void setShipEmail(String shipEmail)
    {
        this.shipEmail = shipEmail;
    }

    public String getShipPhoneNo()
    {
        return shipPhoneNo;
    }

    public void setShipPhoneNo(String shipPhoneNo)
    {
        this.shipPhoneNo = shipPhoneNo;
    }

    public String getShipCity()
    {
        return shipCity;
    }

    public void setShipCity(String shipCity)
    {
        this.shipCity = shipCity;
    }

    public String getShipCountry()
    {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry)
    {
        this.shipCountry = shipCountry;
    }

    public String getShipPostalCode()
    {
        return shipPostalCode;
    }

    public void setShipPostalCode(String shipPostalCode)
    {
        this.shipPostalCode = shipPostalCode;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
}
