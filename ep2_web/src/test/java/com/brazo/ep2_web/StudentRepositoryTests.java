package com.brazo.ep2_web;

import com.brazo.ep2_web.dao.StudentRepository;
import com.brazo.ep2_web.model.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void test() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        System.out.println(formattedDate);

        //studentRepository.save(new Student("ddd","4","ddd@edu.com",12,formattedDate));
        //studentRepository.save(new Student("eee","5","eee@edu.com",12,formattedDate));

        Assert.assertEquals(3, studentRepository.findAll().size());
        Assert.assertEquals("ddd", studentRepository.findByStuEmail("ddd@edu.com").getStuName());
        studentRepository.delete(studentRepository.findByStuName("eee"));
    }
}
