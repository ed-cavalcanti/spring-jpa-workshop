package com.testspring.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.testspring.course.entities.Order;
import com.testspring.course.entities.User;
import com.testspring.course.entities.enums.OrderStatus;
import com.testspring.course.repositories.OrderRepository;
import com.testspring.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User(null, "Jon Snow", "jon@gmail.com", "999999999", "123456");
        User user2 = new User(null, "Eddard Stark", "edstark@gmail.com", "988888888", "123456");

        Order or1 = new Order(null, Instant.parse("2024-01-06T19:53:07Z"), OrderStatus.PAID, user1);
        Order or2 = new Order(null, Instant.parse("2024-01-02T03:42:10Z"), OrderStatus.WAITING_PAYMENT, user2);
        Order or3 = new Order(null, Instant.parse("2024-01-06T15:21:22Z"), OrderStatus.WAITING_PAYMENT, user1);

        userRepository.saveAll(Arrays.asList(user1, user2));
        orderRepository.saveAll(Arrays.asList(or1, or2, or3));
    }

}
