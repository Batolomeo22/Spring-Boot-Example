package com.brazo.ep2_web.dao;

import com.brazo.ep2_web.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByStuName(String name);

    Student findByAge(int age);

    Student findByStuEmail(String email);
}
