package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Entity
@Table(name = "ROOM")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "lessons"})
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int room_id;
    private String number;

    @OneToMany(targetEntity = Lesson.class, mappedBy = "room")
    private List<Lesson> lessons;

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
