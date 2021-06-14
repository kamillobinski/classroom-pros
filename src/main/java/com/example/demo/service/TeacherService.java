package com.example.demo.service;

import com.example.demo.entity.Room;
import com.example.demo.entity.Subject;
import com.example.demo.repository.TeacherRepository;;
import com.example.demo.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private RoomService roomService;

    public Model addNewTeacher(Model model, String firstName, String lastName, String title){
        // Check if input values are not empty
        if (!firstName.equals("") && !lastName.equals("") && !title.equals("")) {
            // Creates new teacher based on the entered data in the form
            Teacher newTeacher = new Teacher();
            newTeacher.setFirst_name(firstName);
            newTeacher.setLast_name(lastName);
            newTeacher.setTitle(title);
            teacherRepository.save(newTeacher);
        }else {
            if(firstName.equals("") || lastName.equals("") || title.equals("")) {
                model.addAttribute("message", "Could not add teacher without data.");
            }
        }
        return model;
    }

    public Model getTeachers(Model model){
        List<Teacher> allTeachers = teacherRepository.findAll();
        model.addAttribute("allTeachers", allTeachers);

        List<Subject> allSubjects = subjectService.getSubjects();
        model.addAttribute("allSubjects", allSubjects);

        List<Room> allRooms = roomService.getRooms();
        model.addAttribute("allRooms",allRooms);

        return model;
    }

    public void deleteTeacher(int id){
        teacherRepository.deleteById(id);
    }
}
