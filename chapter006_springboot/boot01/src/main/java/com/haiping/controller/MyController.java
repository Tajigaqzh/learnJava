package com.haiping.controller;

import com.haiping.dao.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tony
 * @version 1.0
 * @date 2022-11-05 22:01
 */
@RestController
public class MyController {


    @RequestMapping("/hello")
    public Student hello(){
        System.out.println("hello SpringBoot");
        return new Student(1,"zs","3");
    }
}
