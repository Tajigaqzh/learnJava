package com.haiping.mapper;

import com.haiping.pojo.Student;

import java.util.List;

/**
 * @author tony
 * @version 1.0
 * @date 2022-11-04 14:22
 */
public interface StudentMapper {
    /**
     * 查询所有
     * @return
     */
    List<Student> findAll();
}
