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

    // Upload
    @RequestMapping("/upload")
    public String getAddItem() {
        return "upload";
    }

    // All plans
    @RequestMapping("/plans")
    public String getPlans(Model model) {
        List<Plan> allPlans = planService.getAllPlans();
        model.addAttribute("allPlans", allPlans);
        return "plans";
    }
}
