package com.example.demo.controller;

import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.repository.TeacherRepository;
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

    @RequestMapping("/plan-{reqPlanId}")
    public String getRequestedPlan(Model model, @PathVariable int reqPlanId) {
        // Data displayed in table
        List<Hour> allHours = hourService.getHours();
        List<Lesson> mondayLessons = lessonService.getLessonsForSpecificDayAndPlan("Monday", reqPlanId);
        List<Lesson> tuesdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Tuesday", reqPlanId);
        List<Lesson> wednesdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Wednesday", reqPlanId);
        List<Lesson> thursdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Thursday", reqPlanId);
        List<Lesson> fridayLessons = lessonService.getLessonsForSpecificDayAndPlan("Friday", reqPlanId);

        // Toolbar list
        List<Plan> allPlans = planService.getAllPlans();

        /* Name used to display on page
         * Usage:
         *  - renaming plan
         *  - lesson editing */
        Plan currentPlan = planService.getPlanById(reqPlanId);

        // Used to fill lesson editor form
        List<Subject> allSubjects = subjectRepository.findAll();
        List<Teacher> allTeachers = teacherRepository.findAll();
        List<Room> allRooms = roomRepository.findAll();

        model.addAttribute("hourData", allHours);
        model.addAttribute("mondayData", mondayLessons);
        model.addAttribute("tuesdayData", tuesdayLessons);
        model.addAttribute("wednesdayData", wednesdayLessons);
        model.addAttribute("thursdayData", thursdayLessons);
        model.addAttribute("fridayData", fridayLessons);
        model.addAttribute("allPlans", allPlans);
        model.addAttribute("currentPlan", currentPlan);
        model.addAttribute("allSubjects", allSubjects);
        model.addAttribute("allTeachers", allTeachers);
        model.addAttribute("allRooms", allRooms);

        return "plan";
    }

    @RequestMapping("/rename-plan")
    public String renamePlan(@RequestParam("planId") int id, @RequestParam("planName") String name) {
        Plan existingPlan = planService.getPlanById(id);
        existingPlan.setName(name);
        planService.savePlan(existingPlan);

        return "redirect:/plan-" + id;
    }

    @Transactional
    @RequestMapping("/delete-plan-{planId}")
    public String deletePlan(@PathVariable("planId") int id) {
        // Delete all lessons from current plan
        lessonService.deleteLessonByPlanId(id);
        // Delete plan
        planService.deletePlanById(id);

        return "redirect:/plans";
    }

}
