package com.testspring.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.testspring.course.entities.Category;
import com.testspring.course.entities.Order;
import com.testspring.course.entities.OrderItem;
import com.testspring.course.entities.Payment;
import com.testspring.course.entities.Product;
import com.testspring.course.entities.User;
import com.testspring.course.entities.enums.OrderStatus;
import com.testspring.course.repositories.CategoryRepository;
import com.testspring.course.repositories.OrderItemRepository;
import com.testspring.course.repositories.OrderRepository;
import com.testspring.course.repositories.ProductRepository;
import com.testspring.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {
        Category cat1 = new Category(null, "Armors");
        Category cat2 = new Category(null, "Swords");
        Category cat3 = new Category(null, "Mount");

        Product p1 = new Product(null, "Longsword", "Longsword description", 199.99, "");
        Product p2 = new Product(null, "Leather Boots", "Boots description", 45.00, "");
        Product p3 = new Product(null, "Horse", "Horse description", 2000.00, "");
        Product p4 = new Product(null, "Iron Helmet", "Iron Helmet description", 68.50, "");
        Product p5 = new Product(null, "Ice sword", "Ice sword description", 459.90, "");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        p1.getCategories().add(cat2);
        p2.getCategories().add(cat1);
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat1);
        p5.getCategories().add(cat2);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        User user1 = new User(null, "Jon Snow", "jon@gmail.com", "999999999", "123456");
        User user2 = new User(null, "Eddard Stark", "edstark@gmail.com", "988888888", "123456");

        Order or1 = new Order(null, Instant.parse("2024-01-06T19:53:07Z"), OrderStatus.PAID, user1);
        Order or2 = new Order(null, Instant.parse("2024-01-02T03:42:10Z"), OrderStatus.WAITING_PAYMENT, user2);
        Order or3 = new Order(null, Instant.parse("2024-01-06T15:21:22Z"), OrderStatus.WAITING_PAYMENT, user1);

        userRepository.saveAll(Arrays.asList(user1, user2));
        orderRepository.saveAll(Arrays.asList(or1, or2, or3));

        OrderItem oi1 = new OrderItem(or1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(or1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(or2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(or3, p5, 2, p5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

        Payment pay1 = new Payment(null, Instant.parse("2024-01-06T21:53:07Z"), or1);
        or1.setPayment(pay1);

        orderRepository.save(or1);
    }

}
