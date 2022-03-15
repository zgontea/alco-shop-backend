package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;

@Component
public class RunAtStart {
    private final CategoryRepository categoryRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public RunAtStart(CategoryRepository categoryRepository, OrderDetailRepository orderDetailRepository,
            OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) {
        super();
        this.categoryRepository = categoryRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void runAtStart() {

        User user1 = new User("Jan", "Kowalski", "jankowalski@gmail.com", "12345", false);
        User user2 = new User("Marta", "Zalewska", "siema@lol.pl", "doripala", true);
        userRepository.save(user1);
        userRepository.save(user2);

        Category pipka = new Category();
        pipka.setCategoryName("Wodeczki");
        pipka.setDescription("Pipedeczki");

        categoryRepository.save(pipka);

        Date date = new Date();
        Product product = new Product("Zubrowka", "image1", new BigDecimal("7.50"), pipka);

        productRepository.save(product);

        OrderDetail orderDetail = new OrderDetail();
        Set<OrderDetail> orderDetails = new HashSet<>();
        orderDetails.add(orderDetail);

        Order order = new Order("Dori Palka", "lol@wp.pl", null, "JedlinaSciernisko", "Polako", "95-020", date, user1,
                orderDetails);

        orderDetail.setPrice(new BigDecimal("20"));
        orderDetail.setProduct(product);
        orderDetail.setQuantity(1);
        orderDetail.setOrder(order);

        orderRepository.save(order);

    }
}
