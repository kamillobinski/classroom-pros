package com.example.demo.controller;

import com.example.demo.entity.Plan;
import com.example.demo.service.LessonService;
import com.example.demo.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

@Controller
public class PlanController {

    @Autowired
    private PlanService planService;

    @Autowired
    private LessonService lessonService;

    // Change plan name
    @RequestMapping("/change-current-plan-name")
    public String changePlanName(Model model, @RequestParam("current-plan-id") int id, @RequestParam("changed-plan-name") String name) {
        Plan existingPlan = planService.getPlanById(id);
        existingPlan.setName(name);

        planService.savePlan(existingPlan);

        return "redirect:/test-home-" + id;
    }

    @Transactional
    @RequestMapping("/delete-current-plan-{current-plan-id}")
    public String deleteCurrentPlan(Model model, @PathVariable("current-plan-id") int id) {
        lessonService.deleteLessonByPlanId(id);
        planService.deletePlanById(id);

        return "redirect:/test-home";
    }

}
