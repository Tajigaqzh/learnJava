package com.haiping.dao;

import com.haiping.domain.Man;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tony
 * @version 1.0
 * @date 2022-11-07 16:56
 */
@Repository
public interface ManDao {
    /**
     * 查询所有man
     * @return List<Man>
     */
    @Select("select * from man")
    List<Man> findAll();

    /**
     * 添加
     * @param man man
     */
    @Insert("insert into man values (null,#{name},#{sex},#{address})")
    void add(Man man);
}
