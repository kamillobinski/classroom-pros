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
    @RequestMapping("/test-home")
    public String getTestHomepage(Model model) {
        List<Lesson> mondayLessons = lessonService.getLessonsFromSpecificDayOfTheWeek("Monday");
        model.addAttribute("mondayData", mondayLessons);

        List<Lesson> tuesdayLessons = lessonService.getLessonsFromSpecificDayOfTheWeek("Tuesday");
        model.addAttribute("tuesdayData", tuesdayLessons);

        List<Lesson> wednesdayLessons = lessonService.getLessonsFromSpecificDayOfTheWeek("Wednesday");
        model.addAttribute("wednesdayData", wednesdayLessons);

        List<Lesson> thursdayLessons = lessonService.getLessonsFromSpecificDayOfTheWeek("Thursday");
        model.addAttribute("thursdayData", thursdayLessons);

        List<Lesson> fridayLessons = lessonService.getLessonsFromSpecificDayOfTheWeek("Friday");
        model.addAttribute("fridayData", fridayLessons);

        Plan currentPlan = planService.getPlanById(1);
        model.addAttribute("plan", currentPlan);

        List<Hour> allHours = hourService.getHours();
        model.addAttribute("hourData", allHours);
        return "homepage-test";
    }
}
