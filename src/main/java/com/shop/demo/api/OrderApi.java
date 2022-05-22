package com.shop.demo.api;

import java.util.Optional;

import com.shop.demo.model.Order;
import com.shop.demo.service.OrderManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin
@RequestMapping("/api/orders")
public class OrderApi {

	@Autowired
	private OrderManager orderManager;

	@Secured({"ROLE_ADMIN"})
	@GetMapping("/all")
	public Iterable<Order> getAll() {
		return orderManager.findAll();
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/id")
	public Optional<Order> getById(@RequestParam Long index) {
		return orderManager.findById(index);
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping(value = "/{orderId}")
	public Optional<Order> getId(@PathVariable("orderId") Long orderId) {
		return orderManager.findById(orderId);
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/save")
	public Order add(@RequestBody Order order) {
		return orderManager.save(order);
	}

	@Secured({"ROLE_ADMIN"})
	@PutMapping("/upd")
	public Order update(@RequestBody Order order) {
		return orderManager.save(order);
	}

	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/del")
	public void delete(@RequestParam Long index) {
		orderManager.deleteById(index);
	}
}
