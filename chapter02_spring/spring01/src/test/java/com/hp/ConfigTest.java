package com.hp;

import com.hp.config.Config;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;

/**
 * @author tony
 * @version 1.0
 * @date 2022-11-06 19:03
 */
public class ConfigTest {
    @Test
    public void getConnectionTest(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Object connection = context.getBean("Connection");
        Connection bean = context.getBean(Connection.class);
        System.out.println(bean);
        Class<?> aClass = connection.getClass();
        System.out.println(aClass);
    }
}
