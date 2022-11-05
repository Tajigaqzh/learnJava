package com.hp.dao;

import com.hp.domain.Student;

/**
 * @author tony
 * @version 1.0
 * @date
 */
public interface StudentDao {
    /**
     * 根据id查询
     * @param id
     * @return
     */
    Student findById(int id);
}
