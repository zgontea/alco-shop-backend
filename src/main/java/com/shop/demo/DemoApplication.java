package com.shop.demo;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.shop.demo.filter.JwtFilter;

import com.shop.demo.model.Category;
import com.shop.demo.model.Product;
import com.shop.demo.repo.CategoryRepository;
import com.shop.demo.repo.ProductRepository;
import com.shop.demo.service.CategoryManager;
import com.shop.demo.service.ProductManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(DemoApplication.class, args);
//		CategoryRepository repo = context
//				.getBean(CategoryRepository.class);
//		CategoryManager categoryManager = new CategoryManager(repo);
//		ProductRepository repo2 = context
//				.getBean(ProductRepository.class);
//		ProductManager productManager = new ProductManager(repo2);
//
//		Category category = new Category();
//		category.setCategoryName("Wódkil");
//		category.setDescription("wódki czyste do 40%");
//
//		Product product = new Product();
//		product.setImage("images/zubrowka.jpg");
//		product.setUnitPrice(new BigDecimal("24.99"));
//		product.setName("Żubrówka");
//		product.setCategory(category);
//		productManager.save(product);
//
//		Product product2 = new Product();
//		product2.setImage("images/stock.jpg");
//		product2.setUnitPrice(new BigDecimal("25.99"));
//		product2.setName("Stock");
//		product.setCategory(category);
//		product2.setCategory(category);

//		Set<Product> products = new HashSet<>();
//		products.add(product);
//		products.add(product2);
//		category.setProducts(products);
//		categoryManager.save(category);


//		productManager.save(product2);
//		productManager.save(product);

	}

//	@Bean
//	public FilterRegistrationBean filterRegistrationBean() {
//		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>();
//		filterRegistrationBean.setFilter(new JwtFilter());
//
//		filterRegistrationBean.setUrlPatterns(Collections.singleton("/api/orders/*"));
//
//		return filterRegistrationBean;
//	}

//	@EventListener(ApplicationReadyEvent.class)
//    public void runAtStart() {
//        User user = new User();
//        user.setAdmin(true);
//        user.setEmail("janekgontarek@gmail.com");
//        user.setName("Zbyszko");
//        user.setSurname("TrzyCytryny");
//        user.setPassword(new BCryptPasswordEncoder().encode("123"));
//
//        userRepository.save(user);
//    }

}
