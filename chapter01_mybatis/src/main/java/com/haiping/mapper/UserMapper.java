package com.haiping.mapper;

import com.haiping.pojo.User;

import java.util.List;

/**
 * @author tony
 * @version 1.0
 * @date 2022-11-03 21:55
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

    /**
     * 根据条件查询
     * @param user
     * @return
     */
    List<User> findByCondition(User user);
}
