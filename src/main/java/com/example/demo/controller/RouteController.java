package com.example.demo.controller;

import com.example.demo.entity.Hour;
import com.example.demo.service.HourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class RouteController {

    @Autowired
    private HourService hourService;

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
    public String getTestHomepage(Model model) {
        List<Hour> allHours = hourService.getHours();
        model.addAttribute("hourData", allHours);
        return "homepage-test";
    }
}
