package com.haiping.mapper;

import com.haiping.pojo.User;

import java.util.List;

/**
 * @author tony
 * @version 1.0
 * @dat
 */
public interface UserMapper {
    /**
     *根据页码查询
     * @param startIndex
     * @param pageSize
     * @return List<User>
     */
    List<User> findPage(int startIndex,int pageSize);

    /**
     * 查询所有数据
     * @return
     */
    List<User> findAll();
}
