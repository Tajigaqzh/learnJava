package com.hp.dao;

import com.hp.domain.Student;

/**
 * @author tony
 * @version 1.0
 * @date 2022-11-04 20:22
 */
public class StudentDaoImpl implements StudentDao{
    private StudentDao dao;

    public StudentDaoImpl(){
        System.out.println("studentDaoImpl");
    }
    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public Student findById(int id) {
        System.out.println("studentDaoImpl");
        return new Student(id,"张三","上海");
    }

    public StudentDao getDao() {
        return dao;
    }

    public void setDao(StudentDao dao) {
        this.dao = dao;
    }
}
