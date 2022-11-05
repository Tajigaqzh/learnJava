package com.hp.dao;

/**
 * @author tony
 * @version 1.0
 * @dat
 */
public class StudentDaoFactory1 {
    public static StudentDao getStudentDao(){
        System.out.println("StudentDaoFactory1静态工厂");
        return new StudentDaoImpl();
    }
}
