package com.hp.config;

import com.hp.service.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author tony
 * @version 1.0
 * @date 2022-11-07 12:12
 */
@Configuration
@PropertySource("classpath:db.properties")
public class JdbcConfig {
    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean(name = "Connection")
    public Connection getConnection(StudentService studentService){
        System.out.println(studentService);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql:///mysql",username,password);
        } catch (Exception e) {
            return null;
        }
    }
}
