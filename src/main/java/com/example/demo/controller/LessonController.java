package com.example.demo.controller;

import com.example.demo.repository.*;
import com.example.demo.entity.*;
import com.example.demo.service.HourService;
import com.example.demo.service.LessonService;
import com.example.demo.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LessonController {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private PlanService planService;

    @Autowired
    private HourService hourService;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @RequestMapping("/request-lesson-update-{lessonId}-{planId}")
    public String requestLessonUpdate(Model model, @PathVariable int lessonId, @PathVariable int planId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);

        List<Lesson> mondayLessons = lessonService.getLessonsForSpecificDayAndPlan("Monday", planId);
        List<Lesson> tuesdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Tuesday", planId);
        List<Lesson> wednesdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Wednesday", planId);
        List<Lesson> thursdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Thursday", planId);
        List<Lesson> fridayLessons = lessonService.getLessonsForSpecificDayAndPlan("Friday", planId);

        List<Plan> allPlans = planService.getAllPlans();
        Plan currentPlan = planService.getPlanById(planId);
        List<Hour> allHours = hourService.getHours();

        List<Subject> allSubjects = subjectRepository.findAll();
        List<Teacher> allTeachers = teacherRepository.findAll();
        List<Room> allRooms = roomRepository.findAll();

        model.addAttribute("selectedLessonSubject", lesson.getSubject().getSubject_id());
        model.addAttribute("selectedLessonTeacher", lesson.getTeacher().getTeacher_id());
        model.addAttribute("selectedLessonRoom", lesson.getRoom().getRoom_id());

        model.addAttribute("mondayData", mondayLessons);
        model.addAttribute("tuesdayData", tuesdayLessons);
        model.addAttribute("wednesdayData", wednesdayLessons);
        model.addAttribute("thursdayData", thursdayLessons);
        model.addAttribute("fridayData", fridayLessons);

        model.addAttribute("allPlans", allPlans);
        model.addAttribute("currentPlan", currentPlan);
        model.addAttribute("hourData", allHours);

        model.addAttribute("allSubjects", allSubjects);
        model.addAttribute("allTeachers", allTeachers);
        model.addAttribute("allRooms", allRooms);

        model.addAttribute("editedLessonId", lessonId);
        model.addAttribute("currentPlan", currentPlan);

        return "plan";
    }

    @RequestMapping("/lesson-update")
    public String updateLesson(@RequestParam int lessonId, @RequestParam int planId, @RequestParam int subjectId, @RequestParam int teacherId, @RequestParam int roomId) {

        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
        Subject subject = subjectRepository.findById(subjectId).orElse(null);
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        Room room = roomRepository.findById(roomId).orElse(null);

        lesson.setSubject(subject);
        lesson.setTeacher(teacher);
        lesson.setRoom(room);

        lessonRepository.save(lesson);

        return "redirect:/plan-" + planId;
    }

}
