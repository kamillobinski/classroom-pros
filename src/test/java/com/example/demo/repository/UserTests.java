package com.example.demo.repository;

import com.example.demo.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class UserTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void Should_Get_User_By_Email() {
        String email = "admin@test.com";
        User user = userRepository.findByEmail(email);
        Assertions.assertNotNull(user);
    }

}
