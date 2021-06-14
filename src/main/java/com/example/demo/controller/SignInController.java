package com.example.demo.controller;

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
        return signInService.SignIn(model, email, password);
    }
}
