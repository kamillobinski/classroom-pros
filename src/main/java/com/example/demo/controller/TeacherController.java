package com.example.demo.controller;

import com.example.demo.entity.Teacher;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/add-teacher-action", method = RequestMethod.POST)
    public String addNewTeacher(Model model, @RequestParam("add-teacher-firstname-input") String firstName, @RequestParam("add-teacher-lastname-input") String lastName, @RequestParam("add-teacher-title-input") String title) {

        // Check if input values are not empty
        if (firstName != "" && lastName != "" && title != "") {

            // Creates new teacher based on the entered data in the form
            Teacher newTeacher = new Teacher();
            newTeacher.setFirst_name(firstName);
            newTeacher.setLast_name(lastName);
            newTeacher.setTitle(title);

            teacherService.addNewTeacher(newTeacher);
        }else {
            if(firstName == "" || lastName == "" || title == "") {
                model.addAttribute("message", "Could not add teacher without data.");
            }
        }
        return "admin-panel";
    }

    @GetMapping("/lesson-manager")
    public String findAllTeachers(Model model){
        List<Teacher> allTeachers = teacherService.getTeachers();
        model.addAttribute("allTeachers", allTeachers);

        return "lesson-manager";
    }
}