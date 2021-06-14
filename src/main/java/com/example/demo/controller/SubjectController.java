package com.example.demo.controller;

import com.example.demo.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value = "/add-subject-action", method = RequestMethod.POST)
    public String addNewSubject(Model model, @RequestParam("add-subject-name-input") String subjectName) {
        subjectService.addNewSubject(model, subjectName);
        return "redirect:/lesson-manager";
    }

}
