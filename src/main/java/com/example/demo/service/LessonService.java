package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HourRepository hourRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private Subject subject;

    @Autowired
    private Teacher teacher;

    @Autowired
    private Room room;

    @Autowired
    private Hour hour;

    @Autowired
    private Group group;

    public Lesson saveLesson (Lesson lesson)
    {
        return lessonRepository.save(lesson);
    }

    public List<Lesson> saveLessons(List<Lesson> lessons){
        return lessonRepository.saveAll(lessons);
    }

    public Lesson getLessonById(int id){
        return lessonRepository.findById(id).orElse(null);
    }

    public List<Lesson> getLessons(){
        return lessonRepository.findAll();
    }

    public String deleteLesson(int id){
        lessonRepository.deleteById(id);
        return "Lesson was successfully removed!";
    }


    /*
   Requires further work.
   Objects might not be saved in cascade after initial Lesson save.
   If that the case each objectRepository need to be saved individually
     */
    @Transactional
    public Lesson updateLesson(Lesson lesson){
        Lesson existingLesson = lessonRepository.findById(lesson.getId()).orElse(null);

        int id = existingLesson.getId();

        Subject existingSubject = subjectRepository.getOne(id);
        existingSubject.setSubjectName(subject.getSubjectName());
        subjectRepository.save(existingSubject);

        Teacher existingTeacher = teacherRepository.getOne(id);
        existingTeacher.setFirst_name(teacher.getFirst_name());
        existingTeacher.setLast_name(teacher.getLast_name());
        existingTeacher.setTitle(teacher.getTitle());
        teacherRepository.save(existingTeacher);

        Room existingRoom = roomRepository.getOne(id);
        existingRoom.setNumber(room.getNumber());
        roomRepository.save(existingRoom);

        Hour existingHour = hourRepository.getOne(id);
        existingHour.setStart(hour.getStart());
        existingHour.setEnd(hour.getEnd());
        hourRepository.save(existingHour);

        Group existingGroup = groupRepository.getOne(id);
        existingGroup.setGroupName(group.getGroupName());
        existingGroup.setGroupQuantity(group.getGroupQuantity());
        groupRepository.save(existingGroup);

        return lessonRepository.save(existingLesson);

    }
}
