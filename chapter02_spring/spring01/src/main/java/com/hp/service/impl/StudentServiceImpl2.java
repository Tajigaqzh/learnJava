package com.hp.service.impl;

import com.hp.dao.StudentDao;
import com.hp.service.StudentService;

/**
 * @author tony
 * @version 1.0
 * @dat
 */
public class StudentServiceImpl2 implements StudentService {
    private StudentDao studentDao;

    /**构造方法注入
     * @param studentDao
     */
    public StudentServiceImpl2(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
}
