package com.example.demo.repository;

import com.example.demo.entity.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class SubjectTests {

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    public void Should_Create_Subject() {
        String subName = "testSubject";

        Subject subject = new Subject();
        subject.setSubjectName(subName);
        subjectRepository.save(subject);

        Assertions.assertNotNull(subjectRepository.findBySubjectName(subName));
    }

}
