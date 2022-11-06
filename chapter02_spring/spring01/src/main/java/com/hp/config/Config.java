package com.hp.config;

import com.hp.service.StudentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

/**
 * @author tony
 * @version 1.0
 * @date 2022-11-06 18:55
 */
@Configuration
@ComponentScan("com.hp")
//扫描配置文件
//@PropertySource("classpath:db.properties")
public class Config {

    //    bean是可以传递参数的这里用studentService演示
//    @Qualifier("Connection")
    @Bean(name = "Connection")
    public Connection getConnection(StudentService studentService){
        System.out.println(studentService);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql:///mysql","root","123456");
        } catch (Exception e) {
            return null;
        }
    }
}
