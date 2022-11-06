package com.hp.service.impl;

import com.hp.dao.StudentDao;
import com.hp.domain.Student;
import com.hp.service.StudentService;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author tony
 * @version 1.0
 * @date 2022-11-06 18:19
 */
public class StudentServiceImpl3 implements StudentService {
    private StudentDao studentDao;
    private String name;
    private int count;
    private List<String> names;
    private List<Student> students;
    private Set<Student> studentSet;
    private Map<String,String> stringMap;
    private Map<String,Student> studentMap;
    private Properties properties;

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    public Map<String, String> getStringMap() {
        return stringMap;
    }

    public void setStringMap(Map<String, String> stringMap) {
        this.stringMap = stringMap;
    }

    public Map<String, Student> getStudentMap() {
        return studentMap;
    }

    public void setStudentMap(Map<String, Student> studentMap) {
        this.studentMap = studentMap;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "StudentServiceImpl3{" +
                "studentDao=" + studentDao +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", names=" + names +
                ", students=" + students +
                ", studentSet=" + studentSet +
                ", stringMap=" + stringMap +
                ", studentMap=" + studentMap +
                ", properties=" + properties +
                '}';
    }
}
