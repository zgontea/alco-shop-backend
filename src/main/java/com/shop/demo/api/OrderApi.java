package com.shop.demo.api;

import java.util.Optional;

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

import com.shop.demo.model.Order;
import com.shop.demo.model.OrderDetail;
import com.shop.demo.service.OrderDetailManager;
import com.shop.demo.service.OrderManager;
import com.shop.demo.wrapper.OrderWrapper;

@RestController
@CrossOrigin
@RequestMapping("/api/orders")
public class OrderApi {

	@Autowired
	private OrderManager orderManager;

	@Autowired
	private OrderDetailManager orderDetailManager;

	@Secured({"ROLE_ADMIN"})
	@GetMapping("/all")
	public Iterable<Order> getAll() {
		return orderManager.findAll();
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/id")
	public Iterable<OrderDetail> getByUserIdWithStatusDraft(@RequestParam Long userId) {
		Iterable<OrderDetail> orderDetails = null;
		try {
			Order order = orderManager.findByUserIdWithStatusDraft(userId);
			orderDetails = orderDetailManager.getByOrder(order);
		} catch (Exception e) {
			System.err.println("No order in status Draft for this user");
		}
		return orderDetails;
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/userOrder")
	public Iterable<Order> getAllOrdersByUserId(@RequestParam Long userId) {
		Iterable<Order> orders = null;
		try {
			orders = orderManager.findByUserId(userId);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return orders;
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

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/sendOrder")
	public OrderWrapper sendOrder(@RequestBody OrderWrapper order) {
		System.out.println(order.toString());
		Order orderToUpdate = Order.builder()
			.shipAddress(order.getShipAddress())
			.shipCity(order.getShipCity())
			.shipPostalCode(order.getShipPostalCode())
			.shipPhoneNo(order.getShipPhoneNo())
			.shipEmail(order.getShipEmail())
			.totalPrice(order.getTotalPrice())
			.user(order.getUser())
			.build();
			
		try {
			orderManager.sendOrder(orderToUpdate);
		} catch (Exception e) {
			System.err.println("No order in status Draft for this user");
		}
		return order;
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
