package com.example.demo.controller;
import com.example.demo.entity.Plan;
import com.example.demo.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class RouteController {

    @Autowired
    private PlanService planService;

    // Sign in page
    @RequestMapping(value={"/", "/login", "sign-in"} , method = RequestMethod.GET)
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

    // Admin panel old version
    @RequestMapping("/admin-panel-old")
    public String getAdminPanelOld() {
        return "admin-panel-old";
    }

    // All plans
    @RequestMapping("/plans")
    public String getPlans(Model model) {
        List<Plan> allPlans = planService.getAllPlans();
        model.addAttribute("allPlans", allPlans);
        return "plans";
    }

    //Admin panel new version
    @RequestMapping("/admin-panel")
    public String getAdminPanel() {
        return "admin-panel";
    }
}
