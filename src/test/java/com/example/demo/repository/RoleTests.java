package com.example.demo.repository;

import com.example.demo.entity.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class RoleTests {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void Should_Get_Admin_Role() {
        Role role = roleRepository.findByRole("ADMIN");
        Assertions.assertEquals("ADMIN", role.getRole());
        Assertions.assertEquals(0, role.getId());
    }

    @Test
    public void Should_Get_User_Role() {
        Role role = roleRepository.findByRole("USER");
        Assertions.assertEquals("USER", role.getRole());
        Assertions.assertEquals(1, role.getId());
    }

}
