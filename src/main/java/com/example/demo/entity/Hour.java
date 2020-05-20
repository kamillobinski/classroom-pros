package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "HOUR")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "lessons"})
public class Hour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hour_id", unique = true, nullable = false)
    private int hour_id;
    private String start;
    private String end;

    @OneToMany(targetEntity = Lesson.class, mappedBy = "hour")
    private List<Lesson> lessons;

    public int getHour_id() {
        return hour_id;
    }

    public void setHour_id(int hour_id) {
        this.hour_id = hour_id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
