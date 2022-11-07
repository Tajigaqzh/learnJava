package com.hp;

import com.hp.dao.StudentDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author tony
 * @version 1.0
 * @date 2022-11-05 11:53
 */
public class ContainerTest {
    @Test
    public void getContainer(){
        /*
        String pathString = "C:\\Users\\tony\\Desktop\\learnJava\\chapter02_spring\\spring01\\src\\main\\resources\\bean.xml";
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(pathString);
        System.out.println(context);*/

        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        StudentDao bean = context.getBean(StudentDao.class);
        System.out.println(bean);
    }

}
