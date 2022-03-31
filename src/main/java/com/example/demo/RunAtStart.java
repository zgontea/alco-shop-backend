package com.example.demo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import com.example.demo.entities.Category;
import com.example.demo.entities.Order;
import com.example.demo.entities.OrderDetail;
import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.OrderDetailRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RunAtStart
{
    private final CategoryRepository categoryRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public RunAtStart(CategoryRepository categoryRepository, OrderDetailRepository orderDetailRepository,
                      OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository)
    {
        super();
        this.categoryRepository = categoryRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void runAtStart()
    {

        User user1 = User.builder().name("Marek").surname("Kowalski").email("marekkowalski@gmail.com").
                password("lulPassword").admin(false).build();
        userRepository.save(user1);

        Category category = Category.builder().categoryName("Szampan").description("Bezalkoholowy").build();

        categoryRepository.save(category);

        Product product = Product.builder().name("Pikolo").category(category).image("img1").
                unitPrice(new BigDecimal("19.99")).build();

        productRepository.save(product);

        Set<OrderDetail> orders = new HashSet<OrderDetail>();

        Date data = new Date();
        Order order = Order.builder().shipEmail("exampleemail@gmail.com").shipCountry("Poland").
                shipPhoneNo("123456789").shipCity("Lodz").shipPostalCode("95-040").
                orderDate(data).shipTo("Radom").user(user1).orderDetails(orders).build();

        orderRepository.save(order);

        OrderDetail orderDetail = OrderDetail.builder().product(product).
                price(new BigDecimal("19.99")).quantity(2).order(order).build();
        orders.add(orderDetail);
        orderDetailRepository.save(orderDetail);


    }
}
