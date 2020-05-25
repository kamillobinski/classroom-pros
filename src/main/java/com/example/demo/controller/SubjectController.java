package com.example.demo.controller;

import com.example.demo.entity.Subject;
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

        // Check if input values are not empty
        if (subjectName != "") {

            // Creates new subject based on the entered data in the form
            Subject newSubject = new Subject();
            newSubject.setSubjectName(subjectName);

            subjectService.addNewSubject(newSubject);
        }else {
            if(subjectName == "") {
                model.addAttribute("message", "Could not add subject without data.");
            }
        }
        return "upload";
    }
}
