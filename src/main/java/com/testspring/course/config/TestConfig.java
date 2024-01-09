package com.testspring.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.testspring.course.entities.Category;
import com.testspring.course.entities.Order;
import com.testspring.course.entities.Product;
import com.testspring.course.entities.User;
import com.testspring.course.entities.enums.OrderStatus;
import com.testspring.course.repositories.CategoryRepository;
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

        User user1 = new User(null, "Jon Snow", "jon@gmail.com", "999999999", "123456");
        User user2 = new User(null, "Eddard Stark", "edstark@gmail.com", "988888888", "123456");

        Order or1 = new Order(null, Instant.parse("2024-01-06T19:53:07Z"), OrderStatus.PAID, user1);
        Order or2 = new Order(null, Instant.parse("2024-01-02T03:42:10Z"), OrderStatus.WAITING_PAYMENT, user2);
        Order or3 = new Order(null, Instant.parse("2024-01-06T15:21:22Z"), OrderStatus.WAITING_PAYMENT, user1);

        userRepository.saveAll(Arrays.asList(user1, user2));
        orderRepository.saveAll(Arrays.asList(or1, or2, or3));
    }

}
