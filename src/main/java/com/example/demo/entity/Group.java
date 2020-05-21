package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Entity
@Table(name = "GROUPS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "lessons"})
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groups_id", unique = true, nullable = false)
    private int groups_id;

    private String groupName;
    private int groupQuantity;

    @OneToMany(targetEntity = Lesson.class, mappedBy = "group")
    private List<Lesson> lessons;

    public int getGroup_id() {
        return groups_id;
    }

    public void setGroup_id(int group_id) {
        this.groups_id = group_id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupQuantity() {
        return groupQuantity;
    }

    public void setGroupQuantity(int groupQuantity) {
        this.groupQuantity = groupQuantity;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}

