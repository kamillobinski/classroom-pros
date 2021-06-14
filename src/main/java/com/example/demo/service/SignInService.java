package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class SignInService {

    @Autowired
    private UserRepository userRepository;

    public String SignIn(Model model, String email, String password) {
        // Gets registered user with specific email from database
        // If user not in database it will remain as an empty object
        User existingUser = CheckUserCredencials(email);

        // Comparing user inputs with correct data
        // If user does not exist or entered password is wrong - stay on sign-in page,
        // If the entered password is correct - go to homepage.
        if (existingUser != null && existingUser.getPassword().equals(password) && existingUser.isAdmin()==true) {
            return "admin-panel";
        }else if(existingUser != null && existingUser.getPassword().equals(password)){
            return "homepage";
        }else {
            if(email.equals("") && password.equals("")) {
                model.addAttribute("message", "No data entered.");
            } else {
                model.addAttribute("message", "Incorrect email or password.");
            }
            return "sign-in";
        }
    }

    // Called when user has submitted sign-in attempt
    // Object that will be returned will contain data or will remain empty
    public User CheckUserCredencials(String email) {
        return userRepository.findByEmail(email);
    }
}
