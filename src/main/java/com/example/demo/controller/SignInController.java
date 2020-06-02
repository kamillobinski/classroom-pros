package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignInController {

    @Autowired
    private SignInService signInService;

    @RequestMapping(value = "/sign-in-action", method = RequestMethod.POST)
    public String singInToAccount(Model model, @RequestParam("sign-in-email-input") String email, @RequestParam("sign-in-password-input") String password) {
        // Gets registered user with specific email from database
        // If user not in database it will remain as an empty object
        User existingUser = signInService.CheckUserCredencials(email);

        // Comparing user inputs with correct data
        // If user does not exist or entered password is wrong - stay on sign-in page,
        // If the entered password is correct - go to homepage.
        if (existingUser != null && existingUser.getPassword().equals(password) && existingUser.isAdmin()==true) {
            return "admin-panel";
        }else if(existingUser != null && existingUser.getPassword().equals(password)){
            return "homepage";
        }else {
            if(email == "" && password == "") {
                model.addAttribute("message", "No data entered.");
            } else {
                model.addAttribute("message", "Incorrect email or password.");
            }
            return "sign-in";
        }
    }
}
