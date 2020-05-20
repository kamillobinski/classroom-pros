package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController {

    // Sign in page
    @RequestMapping("/sign-in")
    public String getSignIn() {
        return "sign-in.html";
    }

    // Sign up
    @RequestMapping("/sign-up")
    public String getSignUp() {
        return "sign-up.html";
    }

    // Homepage
    @RequestMapping("/homepage")
    public String getHomepage() {
        return "homepage.html";
    }

    // Homepage - TEST VERSION
    @RequestMapping("/test-home")
    public String getTestHomepage() {
        return "homepage-test.html";
    }
}
