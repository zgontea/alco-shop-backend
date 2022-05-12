package com.shop.demo;

import java.util.Arrays;
import java.util.HashSet;

import com.shop.demo.filter.JwtFilter;
import com.shop.demo.repo.CategoryRepository;
import com.shop.demo.repo.ProductRepository;
import com.shop.demo.service.CategoryManager;
import com.shop.demo.service.ProductManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AlcoShopApp {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AlcoShopApp.class, args);
		CategoryRepository repo = context
				.getBean(CategoryRepository.class);
		CategoryManager categoryManager = new CategoryManager(repo);

		ProductRepository repo2 = context
				.getBean(ProductRepository.class);
		ProductManager productManager = new ProductManager(repo2, repo);

	}

	@Bean
	public FilterRegistrationBean<JwtFilter> filterRegistrationBean() {
		FilterRegistrationBean<JwtFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new JwtFilter());

		filterRegistrationBean.setUrlPatterns(new HashSet<String>(
				Arrays.asList(
						"/api/products/del",
						"/api/products/add",
						"/api/products/upd",
						"/api/users/del",
						"/api/users/all",
						"/api/users/id",
						"/api/users/email",
						"/api/users/upd")));

		return filterRegistrationBean;
	}

	// @EventListener(ApplicationReadyEvent.class)
	// public void runAtStart() {
	// User user = new User();
	// user.setAdmin(true);
	// user.setEmail("janekgontarek@gmail.com");
	// user.setName("Zbyszko");
	// user.setSurname("TrzyCytryny");
	// user.setPassword(new BCryptPasswordEncoder().encode("123"));
	//
	// userRepository.save(user);
	// }

}
