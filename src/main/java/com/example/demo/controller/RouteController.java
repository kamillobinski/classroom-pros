package com.example.demo.controller;
import com.example.demo.entity.Plan;
import com.example.demo.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class RouteController {

    @Autowired
    private PlanService planService;

    /* Default first page, currently it will be homepage
     * until spring security is implemented */
    @RequestMapping("/")
    public String getDefaultRoute() {
        return "homepage";
    }

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

    // Upload
    @RequestMapping("/admin-panel")
    public String getAddItem() {
        return "admin-panel";
    }

    // All plans
    @RequestMapping("/plans")
    public String getPlans(Model model) {
        List<Plan> allPlans = planService.getAllPlans();
        model.addAttribute("allPlans", allPlans);
        return "plans";
    }
}
