package com.testspring.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.testspring.course.entities.User;
import com.testspring.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User(null, "Jon Snow", "jon@gmail.com", "999999999", "123456");
        User user2 = new User(null, "Eddard Stark", "edstark@gmail.com", "988888888", "123456");

        userRepository.saveAll(Arrays.asList(user1, user2));
    }

}
