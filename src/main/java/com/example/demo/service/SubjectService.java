package com.example.demo.service;

import com.example.demo.repository.SubjectRepository;
import com.example.demo.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Model addNewSubject(Model model, String subjectName){
        // Check if input values are not empty
        if (subjectName != "") {

            // Creates new subject based on the entered data in the form
            Subject newSubject = new Subject();
            newSubject.setSubjectName(subjectName);
            subjectRepository.save(newSubject);
        }else {
            if (subjectName == "") {
                model.addAttribute("message", "Could not add subject without data.");
            }
        }
        return model;
    }

    public List<Subject> getSubjects(){
        return subjectRepository.findAll();
    }

    public void deleteSubject(int id){
        subjectRepository.deleteById(id);
    }
}
