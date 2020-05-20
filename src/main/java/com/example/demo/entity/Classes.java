package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "CLASSES_TABLE")
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id will be generated automatically
    private int id;
    private int subject_id;
    private int group_id;
    private int hours_id;
    private String date;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", insertable = false,
    updatable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "hour_id")
    private Hour hour;

    public Classes() {
    }

    public Classes(int id, int subject_id, int group_id, int hoursId, String date) {
        this.id = id;
        this.subject_id = subject_id;
        this.group_id = group_id;
        this.hours_id = hoursId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int classesId) {
        this.id = classesId;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subjectId) {
        this.subject_id = subjectId;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int groupId) {
        this.group_id = groupId;
    }

    public int getHours_id() {
        return hours_id;
    }

    public void setHours_id(int hoursId) {
        this.hours_id = hoursId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
