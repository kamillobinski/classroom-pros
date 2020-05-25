package com.example.demo.service;

import com.example.demo.dao.SubjectRepository;
import com.example.demo.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Subject addNewSubject(Subject subject){
        return subjectRepository.save(subject);
    }
}
