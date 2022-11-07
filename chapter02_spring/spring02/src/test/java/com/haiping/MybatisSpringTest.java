package com.haiping;


import com.haiping.domain.Man;
import com.haiping.service.ManService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author tony
 * @version 1.0
 * @date 2022-11-07 15:12
 */

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MybatisSpringTest {
    @Autowired
    private ManService manService;

    @Test
    public void testFindAll(){
        List<Man> all = manService.findAll();
        all.forEach(System.out::println);
    }

    @Test
    public void testFindAll2(){
        List<Man> all = manService.findAll2();
        all.forEach(System.out::println);
    }

}
