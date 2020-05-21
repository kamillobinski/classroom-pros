package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Entity
@Table(name = "DAY")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "lessons"})
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day_id", unique = true, nullable = false)
    private int day_id;
    private String name;

    @OneToMany(targetEntity = Lesson.class, mappedBy = "day")
    private List<Lesson> lessons;

    public int getDay_id() {
        return day_id;
    }

    public void setDay_id(int day_id) {
        this.day_id = day_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
