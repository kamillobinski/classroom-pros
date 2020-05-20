package com.example.demo.dao;

import com.example.demo.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassesRepository extends JpaRepository <Lesson, Integer> {
}
