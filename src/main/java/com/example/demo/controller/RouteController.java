package com.example.demo.controller;

import com.example.demo.entity.Hour;
import com.example.demo.entity.Lesson;
import com.example.demo.entity.Plan;
import com.example.demo.service.HourService;
import com.example.demo.service.LessonService;
import com.example.demo.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class RouteController {

    @Autowired
    private HourService hourService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private PlanService planService;

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
    // Without data
    @RequestMapping("/test-home")
    public String getEmptyTestHomepage(Model model) {
        List<Plan> allPlans = planService.getAllPlans();
        model.addAttribute("allPlans", allPlans);

        return "homepage-test-empty";
    }

    // Homepage - TEST VERSION
    // With actual plan
    @RequestMapping("/test-home-{requestedPlanId}")
    public String getTestHomepage(Model model, @PathVariable int requestedPlanId) {
        List<Lesson> mondayLessons = lessonService.getLessonsForSpecificDayAndPlan("Monday", requestedPlanId);
        model.addAttribute("mondayData", mondayLessons);

        List<Lesson> tuesdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Tuesday", requestedPlanId);
        model.addAttribute("tuesdayData", tuesdayLessons);

        List<Lesson> wednesdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Wednesday", requestedPlanId);
        model.addAttribute("wednesdayData", wednesdayLessons);

        List<Lesson> thursdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Thursday", requestedPlanId);
        model.addAttribute("thursdayData", thursdayLessons);

        List<Lesson> fridayLessons = lessonService.getLessonsForSpecificDayAndPlan("Friday", requestedPlanId);
        model.addAttribute("fridayData", fridayLessons);

        List<Plan> allPlans = planService.getAllPlans();
        model.addAttribute("allPlans", allPlans);

        Plan currentPlan = planService.getPlanById(requestedPlanId);
        model.addAttribute("currentPlan", currentPlan);

        List<Hour> allHours = hourService.getHours();
        model.addAttribute("hourData", allHours);
        return "homepage-test";
    }
}
