package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Entity
@Table(name = "GROUP")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "lessons"})
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int group_id;
    private String groupName;
    private int groupQuantity;

    @OneToMany(targetEntity = Lesson.class, mappedBy = "group")
    private List<Group> groups;

    public Group() {
    }

    public Group(int group_id, String groupName, int groupQuantity) {
        this.group_id = group_id;
        this.groupName = groupName;
        this.groupQuantity = groupQuantity;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
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

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
