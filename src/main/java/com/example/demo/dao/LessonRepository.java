package com.example.demo.dao;

import com.example.demo.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository <Lesson, Integer> {

    List<Lesson> findByDayNameOrderByHourAsc (String day);
    List<Lesson> findByDayNameAndPlanIdOrderByHourAsc (String day, int id);


}
