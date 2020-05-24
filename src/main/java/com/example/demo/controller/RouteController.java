package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController {

    // Sign in page
    @RequestMapping("/sign-in")
    public String getSignIn() {
        return "sign-in";
    }

    // Sign up
    @RequestMapping("/sign-up")
    public String getSignUp() {
        return "sign-up";
    }

    // Homepage
    @RequestMapping("/homepage")
    public String getHomepage() {
        return "homepage";
    }
}
