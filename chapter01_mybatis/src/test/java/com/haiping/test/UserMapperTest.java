package com.haiping.test;

import com.haiping.mapper.UserMapper;
import com.haiping.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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
    private static InputStream inputStream = null;
    private static SqlSession sqlSession = null;

    static {
        try {
            inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
//           读取核心配置文件
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//            创建SqlSessionFactoryBuilder对象
            SqlSessionFactory factory = builder.build(inputStream);
//            SqlSessionFactoryBuilder对象获取SqlSessionFactory对象
            sqlSession = factory.openSession();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindPage() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> result = mapper.findPage(0, 5);
        result.forEach(System.out::println);
        UserMapperTest.close();
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
    }
}
