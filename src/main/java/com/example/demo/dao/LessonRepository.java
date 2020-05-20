package com.example.demo.dao;

import com.example.demo.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository <Lesson, Integer> {

}
