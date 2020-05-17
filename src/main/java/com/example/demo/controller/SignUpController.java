package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @RequestMapping(value = "/sign-up-action", method = RequestMethod.POST)
    public String singUpToAccount(@RequestParam("sign-up-email-input") String email, @RequestParam("sign-up-password-input") String password) {

        // Check if input values are not empty
        if (email != "" && password != "") {

            // Creates new user based on the entered data in the form
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword(password);

            // Check if user already exists in database
            User signedUser = signUpService.checkIfUserExists(email);
            if (signedUser == null) {
                // When user does not exist - save account and return sign-up page
                // Send new user to the database
                signUpService.signUpUser(newUser);
                return "sign-in";
            } else {
                // If exists go to sign-in and don not create new
                return "sign-up";
            }

        } else {
            return "sign-up";
        }
    }
}