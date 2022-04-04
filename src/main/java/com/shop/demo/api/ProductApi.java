package com.shop.demo.api;

import java.util.Optional;

import com.shop.demo.model.Product;
import com.shop.demo.service.ProductManager;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/products")
public class ProductApi {
    
    private ProductManager productManager;

	@Autowired
	public ProductApi(ProductManager productManager) {
		super();
		this.productManager = productManager;
	}

	@GetMapping("/all")
	public Iterable<Product> getAll() {
		return productManager.findAll();
	}

	@GetMapping("/id")
	public Optional<Product> getById(@RequestParam Long index) {
		return productManager.findById(index);
	}

	@GetMapping(value = "/{productId}")
	public Optional<Product> getId(@PathVariable("productId") Long productId) {
		return productManager.findById(productId);
	}

	@PostMapping("/save")
	public Product add(@RequestBody Product product) {
		return productManager.save(product);
	}

	@PutMapping("/upd")
	public Product update(@RequestBody Product product) {
		return productManager.save(product);
	}

	@DeleteMapping("/del")
	public void delete(@RequestParam Long index) {
		productManager.deleteById(index);
	}
}