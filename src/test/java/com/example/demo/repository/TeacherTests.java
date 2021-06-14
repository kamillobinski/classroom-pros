package com.example.demo.repository;

import com.example.demo.entity.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@SpringBootTest
public class TeacherTests {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void Should_Create_Teacher() {
        String firstName = "testFirstName";
        String lastName = "testLastName";
        String title = "testTitle";

        Teacher teacher = new Teacher();
        teacher.setFirst_name(firstName);
        teacher.setLast_name(lastName);
        teacher.setTitle(title);

        teacherRepository.save(teacher);

        Assertions.assertNotNull(teacherRepository.findByTitle(title));
    }

    @Test
    public void Should_Get_All_Teachers() {
        ArrayList<Teacher> expected = new ArrayList<>();
        expected.add(new Teacher(null, null, null));
        expected.add(new Teacher("K.", "Racka", "mgr"));
        expected.add(new Teacher("R.", "Kapturski", "mgr inz."));
        expected.add(new Teacher("S.", "Wszelak", "dr inz."));
        expected.add(new Teacher("M.", "Smietanski", "prof. dr hab."));

        List<Teacher> teachers = teacherRepository.findAll();

        int i = 0;
        for( Teacher teacher : teachers) {
            Assertions.assertEquals(expected.get(i).getFirst_name(), teacher.getFirst_name());
            Assertions.assertEquals(expected.get(i).getLast_name(), teacher.getLast_name());
            Assertions.assertEquals(expected.get(i).getTitle(), teacher.getTitle());
            i++;
        }
    }

    @Test
    public void Should_Delete_Teacher() {
        Teacher teacher = new Teacher("testFirstName", "testLastName", "testTitle");
        teacherRepository.save(teacher);

        teacher = teacherRepository.findByTitle("testTitle");
        teacherRepository.delete(teacher);

        Assertions.assertFalse(teacherRepository.existsById(teacher.getTeacher_id()));
    }

}
