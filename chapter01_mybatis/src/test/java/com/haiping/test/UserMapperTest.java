package com.haiping.test;

import com.haiping.mapper.UserMapper;
import com.haiping.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author tony
 * @version 1.0
 * @date 2022-11-03 21:36
 */
public class UserMapperTest {
    /*private static InputStream inputStream = null;
    private static SqlSession sqlSession = null;
    private static UserMapper mapper = null;

    static {
        try {
            inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
//           读取核心配置文件
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//            创建SqlSessionFactoryBuilder对象
            SqlSessionFactory factory = builder.build(inputStream);
//            SqlSessionFactoryBuilder对象获取SqlSessionFactory对象
            sqlSession = factory.openSession();
            mapper = sqlSession.getMapper(UserMapper.class);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void close() {
        if (sqlSession != null) {
            sqlSession.close();
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

    private InputStream is = null;
    private SqlSession session = null;
    private UserMapper mapper = null;
    @Before
    public void before() throws IOException{
        // （1）读取核心配置文件
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        // （2）创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // （3）SqlSessionFactoryBuilder对象获取SqlSessionFactory对象
        SqlSessionFactory factory = builder.build(is);
        // （4）SqlSessionFactory对象获取SqlSession对象
        session = factory.openSession();
        // （5）获取代理对象
        mapper = session.getMapper(UserMapper.class);
    }
    @After
    public void after() throws IOException {
        // 释放资源
        session.close();
        is.close();
    }
    @Test
    public void testFindPage() {
        List<User> result = mapper.findPage(0, 5);
        result.forEach(System.out::println);
    }

    @Test
    public void testFindAll() {
        List<User> result = mapper.findAll();
        result.forEach(System.out::println);
    }
    @Test
    public void testFindByCondition(){
        User user = new User();
        user.setUsername("广");
        List<User> byCondition = mapper.findByCondition(user);
        byCondition.forEach(System.out::println);
    }
    @Test
    public void testAdd(){
        User user = new User();
        user.setUsername("zz");
        user.setAddress("北京");
        user.setSex("男");
        int i = mapper.addUser(user);
//        提交事务
        session.commit();
//        sqlSession.commit();
        System.out.println(i);
    }


}
