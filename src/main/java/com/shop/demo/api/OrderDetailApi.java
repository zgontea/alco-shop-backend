package com.shop.demo.api;

import java.util.Optional;

import com.shop.demo.model.OrderDetail;
import com.shop.demo.service.OrderDetailManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/orderDetails")
public class OrderDetailApi {

    @Autowired
    private OrderDetailManager orderDetailManager;

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/all")
    public Iterable<OrderDetail> getAll() {
        return orderDetailManager.findAll();
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/id")
    public Optional<OrderDetail> getById(@RequestParam Long index) {
        return orderDetailManager.findById(index);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping(value = "/{orderDetailId}")
    public Optional<OrderDetail> getId(@PathVariable("orderDetailId") Long orderDetailId) {
        return orderDetailManager.findById(orderDetailId);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/save")
    public OrderDetail add(@RequestBody OrderDetail orderDetail) {
        return orderDetailManager.save(orderDetail);
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/upd")
    public OrderDetail update(@RequestBody OrderDetail orderDetail) {
        return orderDetailManager.save(orderDetail);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/del")
    public void delete(@RequestParam Long index) {
        orderDetailManager.deleteById(index);
    }
}
