package com.example.demo.service;

import com.example.demo.entity.Teacher;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Subject addNewSubject(Subject subject){
        return subjectRepository.save(subject);
    }

    public List<Subject> getSubjects(){
        return subjectRepository.findAll();
    }

    public void deleteSubject(int id){
        subjectRepository.deleteById(id);
    }
}
