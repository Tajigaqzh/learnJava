package com.haiping.mapper;

import com.haiping.PageQuery;
import com.haiping.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


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
     * 分页查询
     *
     * @param startIndex 开始索引
     * @param pageSize   每页条数
     * @return
     */
    List<User> findPage1(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    /**
     * 分页查询
     * @param pageQuery 查询页码条i按
     * @return List
     */
    List<User> findPage2(PageQuery pageQuery);

    /**
     * 分页查询3
     * @param params
     * @return
     */
    List<User> findPage3(Map<String, Object> params);


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

    /**
     * 插入user
     * @param user
     * @return int
     */
    int addUser(User user);

    /**
     * 更新user
     * @param user
     */
    void update(User user);

    /**
     * 根据id删除user
     * @param userId
     */
    void delete(int userId);

    /**
     * 根据id查询user
     * @param userId
     * @return
     */
    User findById(int userId);

    /**
     * 根据名字模糊查询
     * @param username
     * @return
     */
    List<User> findByUsernameLike(String username);

    /**
     * 插入2
     * @param user
     */
    void add2(User user);

    /**
     * 查询总条数
     * @return
     */
    int findCount();


    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(int[] ids);

    /**
     * 批量新增 insert into user(username,sex,address) values(),(),();
     * @param user
     */
    void insertBatch(List<User> user);


    /**
     * 多条件查询
     * @param map 查询的条件键值对 键：属性名 值：属性值
     *            select * from user where username = ? and sex = ?
     * @return
     */
    List<User> findUser(@Param("queryMap") Map<String,Object> map);
}
