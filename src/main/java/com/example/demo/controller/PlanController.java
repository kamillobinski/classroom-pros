package com.example.demo.controller;

import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Controller
public class PlanController {

    @Autowired
    private PlanService planService;

    @RequestMapping("/plan-{reqPlanId}")
    public String getRequestedPlan(Model model, @PathVariable int reqPlanId) {
        planService.getPlanById(model, reqPlanId);
        return "plan";
    }

    @RequestMapping("/plan-read-only-{reqPlanId}")
    public String getRequestedPlanReadOnly(Model model, @PathVariable int reqPlanId) {
        planService.getRequestedPlanReadOnly(model, reqPlanId);
        return "plan-read-only";
    }

    @RequestMapping("/rename-plan")
    public String renamePlan(@RequestParam("planId") int id, @RequestParam("planName") String name) {
        planService.renamePlan(id, name);
        return "redirect:/plan-" + id;
    }

    @Transactional
    @RequestMapping("/delete-plan-{planId}")
    public String deletePlan(@PathVariable("planId") int id) {
        planService.deletePlanById(id);
        return "redirect:/plans";
    }

    // Generate new table
    @RequestMapping("/generate-new-table-{plan_id}")
    public String generateNewTable(@PathVariable int plan_id) {
        planService.createNewTable(plan_id);
        return "redirect:/plan-" + plan_id;
    }

    // Generate new plan
    @RequestMapping("/generate-new-plan")
    public String generateNewPlan() {
        int savedPlanId = planService.createNewPlan();
        return "redirect:/generate-new-table-" + savedPlanId;
    }

    @GetMapping("/download-pdf")
    public void downloadPDFResource(HttpServletResponse response) {
        planService.downloadPDFResource(response);
    }
}
