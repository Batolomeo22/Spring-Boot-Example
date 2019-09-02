package com.brazo.ep2_web.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable = false)
    private String stuName;

    @Column(nullable = false ,unique = true)
    private String stuNo;

    @Column(nullable = true)
    private String stuEmail;

    @Column(nullable = true)
    private Integer age;

    @Column(nullable = true)
    private String regTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public Student() {
    }

    public Student(String stuName, String stuNo, String stuEmail, Integer age, String regTime) {
        this.stuName = stuName;
        this.stuNo = stuNo;
        this.stuEmail = stuEmail;
        this.age = age;
        this.regTime = regTime;
    }
}
