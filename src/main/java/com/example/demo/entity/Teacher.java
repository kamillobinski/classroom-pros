package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Entity
@Table(name = "TEACHER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "lessons"})
public class Teacher {

    public Teacher() {

    }

    public Teacher(String fn, String ln, String t) {
        first_name = fn;
        last_name = ln;
        title = t;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id", unique = true, nullable = false)
    private int teacher_id;
    private String title;
    private String first_name;
    private String last_name;

    @OneToMany(targetEntity = Lesson.class, mappedBy = "teacher")
    private List<Lesson> lessons;

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
