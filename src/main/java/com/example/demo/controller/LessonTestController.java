package com.example.demo.controller;

import com.example.demo.entity.Lesson;
import com.example.demo.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LessonTestController {

    @Autowired
    private LessonService lessonService;

    @GetMapping("/lessons")
    public List<Lesson> findAllLessons(){
        return lessonService.getLessons();
    }
}
