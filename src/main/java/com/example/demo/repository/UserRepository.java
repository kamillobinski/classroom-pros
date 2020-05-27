package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
Repository is related to entity User
all methods are implemented from JpaRepository
 */
public interface UserRepository extends JpaRepository <User, Integer> {

    User findByEmail (String email);

}
