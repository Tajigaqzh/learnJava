package com.hp.service.impl;

import com.hp.dao.StudentDao;
import com.hp.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @author tony
 * @version 1.0
 * @date 2022-11-05 15:12
 */
@Service
public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;

    /**
     * setter注入
     * @param studentDao
     */
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
    public StudentServiceImpl(StudentDao dao){
        this.studentDao=dao;
    }

}
