package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @RequestMapping("/request-lesson-update-{lessonId}-{planId}")
    public String requestLessonUpdate(Model model, @PathVariable int lessonId, @PathVariable int planId) {
        lessonService.requestLessonUpdate(model, lessonId, planId);
        return "plan";
    }

    @RequestMapping("/lesson-update")
    public String updateLesson(@RequestParam int lessonId, @RequestParam int planId, @RequestParam int subjectId, @RequestParam int teacherId, @RequestParam int roomId) {
        lessonService.updateLesson(lessonId, planId, subjectId, teacherId, roomId);
        return "redirect:/plan-" + planId;
    }

    @GetMapping("/lessons-view")
    public String findAllLessons(Model model){
        List <Lesson> allLessons = lessonService.getLessons();
        model.addAttribute("allLessons", allLessons);
        return "lessons-view";
    }

}
