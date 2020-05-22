package com.example.demo.controller;

import com.example.demo.entity.Plan;
import com.example.demo.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlanController {

    @Autowired
    private PlanService planService;

    // Change plan name
    @RequestMapping("/change-current-plan-name")
    public String changePlanName(Model model, @RequestParam("current-plan-id") int id, @RequestParam("changed-plan-name") String name) {
        Plan existingPlan = planService.getPlanById(id);
        existingPlan.setName(name);

        planService.savePlan(existingPlan);

        return "redirect:/test-home-" + id;
    }
}
