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

import javax.transaction.Transactional;
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

    // Change plan name
    @RequestMapping("/change-current-plan-name")
    public String changePlanName(Model model, @RequestParam("current-plan-id") int id, @RequestParam("changed-plan-name") String name) {
        Plan existingPlan = planService.getPlanById(id);
        existingPlan.setName(name);

        planService.savePlan(existingPlan);

        return "redirect:/test-home-" + id;
    }

    @RequestMapping("/homepage-{requestedPlanId}")
    public String selectPlan(Model model,@PathVariable int requestedPlanId) {
        List<Plan> plans = planService.getAllPlans();
        model.addAttribute("plans", plans);

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

        // Used in the lesson edit form
        List<Subject> allSubjects = subjectRepository.findAll();
        model.addAttribute("allSubjects", allSubjects);

        List<Teacher> allTeachers = teacherRepository.findAll();
        model.addAttribute("allTeachers", allTeachers);

        List<Room> allRooms = roomRepository.findAll();
        model.addAttribute("allRooms", allRooms);

        return "homepage";
    }

    @RequestMapping("/get-first-plan")
    public String getFirstPlan(Model model){
        int firstPlanId = planService.getAllPlans().get(0).getId();
        System.out.println("clicked");
        return "redirect:/homepage-"+firstPlanId;

    }

    @Transactional
    @RequestMapping("/delete-current-plan-{current-plan-id}")
    public String deleteCurrentPlan(Model model, @PathVariable("current-plan-id") int id) {
        lessonService.deleteLessonByPlanId(id);
        planService.deletePlanById(id);

        return "redirect:/test-home";
    }

}
