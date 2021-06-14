package com.example.demo.repository;

import com.example.demo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TeacherRepository extends JpaRepository <Teacher, Integer> {
    Teacher save(Teacher teacher);
    Teacher findByTitle(String title);
    List<Teacher> findAll();
}
