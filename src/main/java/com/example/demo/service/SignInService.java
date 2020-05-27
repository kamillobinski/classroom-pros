package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInService {

    @Autowired
    private UserRepository userRepository;

    // Called when user has submitted sign-in attempt
    // Object that will be returned will contain data or will remain empty
    public User CheckUserCredencials(String email) {
        return userRepository.findByEmail(email);
    }
}
