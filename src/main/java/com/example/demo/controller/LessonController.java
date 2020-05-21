package com.example.demo.controller;

import com.example.demo.entity.Hour;
import com.example.demo.entity.Lesson;
import com.example.demo.service.HourService;
import com.example.demo.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class LessonTestController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private HourService hourService;

    @GetMapping("/lessons")
    public List<Lesson> findAllLessons(){
        return lessonService.getLessons();
    }

    @RequestMapping(value = "/load-plan-hours", method = RequestMethod.GET)
    public String loadData(Model model) {
        List<Hour> allHours = hourService.getHours();
        System.out.println(allHours.get(1).getStart());
        model.addAttribute("hourData", allHours);
        return "homepage-test";
    }
}
