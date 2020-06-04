package com.example.demo.service;

import com.example.demo.repository.TeacherRepository;;
import com.example.demo.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher addNewTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
    }

    public List<Teacher> getTeachers(){
        return teacherRepository.findAll();
    }
}
