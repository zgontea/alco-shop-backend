package com.shop.demo.api;

import java.util.Optional;

import com.shop.demo.model.OrderDetail;
import com.shop.demo.service.OrderDetailManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/orderDetails")
public class OrderDetailApi {

    private OrderDetailManager orderDetailManager;

    @Autowired
    public OrderDetailApi(OrderDetailManager orderManager) {
        super();
        this.orderDetailManager = orderManager;
    }

    @GetMapping("/all")
    public Iterable<OrderDetail> getAll() {
        return orderDetailManager.findAll();
    }

    @GetMapping("/id")
    public Optional<OrderDetail> getById(@RequestParam Long index) {
        return orderDetailManager.findById(index);
    }

    @GetMapping(value = "/{orderDetailId}")
    public Optional<OrderDetail> getId(@PathVariable("orderDetailId") Long orderDetailId) {
        return orderDetailManager.findById(orderDetailId);
    }

    @PostMapping("/save")
    public OrderDetail add(@RequestBody OrderDetail orderDetail) {
        return orderDetailManager.save(orderDetail);
    }

    @PutMapping("/upd")
    public OrderDetail update(@RequestBody OrderDetail orderDetail) {
        return orderDetailManager.save(orderDetail);
    }

    @DeleteMapping("/del")
    public void delete(@RequestParam Long index) {
        orderDetailManager.deleteById(index);
    }
}
