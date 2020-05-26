package com.example.demo.controller;

import com.example.demo.dao.RoomRepository;
import com.example.demo.dao.SubjectRepository;
import com.example.demo.dao.TeacherRepository;
import com.example.demo.entity.*;
import com.example.demo.service.HourService;
import com.example.demo.service.LessonService;
import com.example.demo.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlanController {

    @Autowired
    private PlanService planService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private HourService hourService;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private RoomRepository roomRepository;

    @RequestMapping("/plan-{reqPlanId}")
    public String getRequestedPlan(Model model, @PathVariable int reqPlanId) {
        // Data displayed in table
        List<Hour> allHours = hourService.getHours();
        List<Lesson> mondayLessons = lessonService.getLessonsForSpecificDayAndPlan("Monday", reqPlanId);
        List<Lesson> tuesdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Tuesday", reqPlanId);
        List<Lesson> wednesdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Wednesday", reqPlanId);
        List<Lesson> thursdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Thursday", reqPlanId);
        List<Lesson> fridayLessons = lessonService.getLessonsForSpecificDayAndPlan("Friday", reqPlanId);

        // Name used to display on page
        // Future use - edit lessons from plan
        Plan currentPlan = planService.getPlanById(reqPlanId);

        model.addAttribute("hourData", allHours);
        model.addAttribute("mondayData", mondayLessons);
        model.addAttribute("tuesdayData", tuesdayLessons);
        model.addAttribute("wednesdayData", wednesdayLessons);
        model.addAttribute("thursdayData", thursdayLessons);
        model.addAttribute("fridayData", fridayLessons);

        model.addAttribute("currentPlan", currentPlan);

        return "plan";
    }

    @RequestMapping("/get-first-plan")
    public String getFirstPlan(Model model){
        int firstPlanId = planService.getAllPlans().get(0).getId();
        System.out.println("clicked");
        return "redirect:/homepage-"+firstPlanId;

    }


}
