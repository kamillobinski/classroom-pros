package com.example.demo.service;

import com.example.demo.dao.LessonRepository;
import com.example.demo.entity.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    public List<Lesson> getLessons(){
        return lessonRepository.findAll();
    }
}
