package com.shop.demo.api;

import java.util.Optional;

import com.shop.demo.model.Order;
import com.shop.demo.service.OrderManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin
@RequestMapping("/api/orders")
public class OrderApi {
    
    private OrderManager orderManager;

	@Autowired
	public OrderApi(OrderManager orderManager) {
		super();
		this.orderManager = orderManager;
	}

	@GetMapping("/all")
	public Iterable<Order> getAll() {
		return orderManager.findAll();
	}

	@GetMapping("/id")
	public Optional<Order> getById(@RequestParam Long index) {
		return orderManager.findById(index);
	}

	@GetMapping(value = "/{orderId}")
	public Optional<Order> getId(@PathVariable("orderId") Long orderId) {
		return orderManager.findById(orderId);
	}

	@PostMapping("/save")
	public Order add(@RequestBody Order order) {
		return orderManager.save(order);
	}

	@PutMapping("/upd")
	public Order update(@RequestBody Order order) {
		return orderManager.save(order);
	}

	@DeleteMapping("/del")
	public void delete(@RequestParam Long index) {
		orderManager.deleteById(index);
	}
}
