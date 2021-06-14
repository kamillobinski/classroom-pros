package com.example.demo.controller;

import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/add-teacher-action", method = RequestMethod.POST)
    public String addNewTeacher(Model model, @RequestParam("add-teacher-firstname-input") String firstName, @RequestParam("add-teacher-lastname-input") String lastName, @RequestParam("add-teacher-title-input") String title) {
        teacherService.addNewTeacher(model, firstName, lastName, title);
        return "redirect:/lesson-manager";
    }

    @GetMapping("/lesson-manager")
    public String findAllTeachers(Model model){
        teacherService.getTeachers(model);
        return "/lesson-manager";
    }
}