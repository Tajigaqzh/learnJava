package com.hp;

import com.hp.config.SpringConfig;
import com.hp.dao.StudentDao;
import com.hp.domain.Student;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;

/**
 * @author tony
 * @version 1.0
 * @date 2022-11-06 19:03
 */
public class ConfigTest {
    @Test
    public void getConnectionTest(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Connection connection = (Connection) context.getBean("Connection");
        System.out.println(connection);
        StudentDao bean = context.getBean(StudentDao.class);
        System.out.println(bean);

    }
    @Test
    public void classTest() throws InstantiationException, IllegalAccessException {
        Class<Student> studentClass = Student.class;
        Student student = studentClass.newInstance();
        student.setId(1);
        System.out.println(student);


    }

    @Test
    public void classTest2() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> aClass = Class.forName("com.hp.domain.Student");
        Student o = (Student)aClass.newInstance();
        System.out.println(o);
    }


}
