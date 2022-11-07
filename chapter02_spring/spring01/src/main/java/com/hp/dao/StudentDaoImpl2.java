package com.hp.dao;

import com.hp.domain.Student;
import org.springframework.stereotype.Repository;

/**
 * @author tony
 * @version 1.0
 * @date 2022-11-04 20:07
 */
@Repository
public class StudentDaoImpl2 implements StudentDao {
    public StudentDaoImpl2() {
        init();
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public Student findById(int id) {
        return null;
    }

    /**
     * 创建时自动执行的方法
     */
    public void init() {
        System.out.println("创建时自动执行的方法:创建StudentDao！！！");
    }

    /**
     * 销毁时自动执行的方法
     */
    public void destroy() {
        System.out.println("销毁StudentDao！！！");
    }


}
