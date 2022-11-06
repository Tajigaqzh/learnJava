package com.haiping.dao;

import java.io.Serializable;

/**
 * @author tony
 * @version 1.0
 * @date 2022-11-06 08:05
 */
public class Student implements Serializable {
    private Integer id;
    private String name;
    private String grade;

    public Student() {
    }

    public Student(Integer id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
