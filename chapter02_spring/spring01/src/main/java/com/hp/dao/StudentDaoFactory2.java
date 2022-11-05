package com.hp.dao;

/**
 * @author tony
 * @version 1.0
 * @date 2022-11-04 19:38
 */
public class StudentDaoFactory2 {
    public StudentDao getStudentDao() {
        System.out.println("StudentDaoFactory2普通工厂");
        return new StudentDaoImpl();
    }
}
