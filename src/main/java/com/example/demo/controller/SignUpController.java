package com.example.demo.controller;

import com.example.demo.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @RequestMapping(value = "/sign-up-action", method = RequestMethod.POST)
    public String singUpToAccount(Model model, @RequestParam("sign-up-email-input") String email, @RequestParam("sign-up-password-input") String password) {
        return signUpService.signUpUser(model, email, password);
    }
}