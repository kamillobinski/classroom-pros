package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "SUBJECT_TABLE")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String subjectName;

    @OneToOne(mappedBy = "SUBJECT_TABLE")
    private Classes classes;

    public Subject() {
    }

    public Subject(int id, String subjectName) {
        this.id = id;
        this.subjectName = subjectName;
    }

    public int getId() {
        return id;
    }

    public void setId(int subjectId) {
        this.id = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
