package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "CLASSES_TABLE")
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id will be generated automatically
    private int classesId;
    private int subjectId;
    private int groupId;
    private int roomId;
    private int hoursId;
    private String date;

    public Classes() {
    }

    public Classes(int classesId, int subjectId, int groupId, int roomId, int hoursId, String date) {
        this.classesId = classesId;
        this.subjectId = subjectId;
        this.groupId = groupId;
        this.roomId = roomId;
        this.hoursId = hoursId;
        this.date = date;
    }

    public int getClassesId() {
        return classesId;
    }

    public void setClassesId(int classesId) {
        this.classesId = classesId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getHoursId() {
        return hoursId;
    }

    public void setHoursId(int hoursId) {
        this.hoursId = hoursId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
