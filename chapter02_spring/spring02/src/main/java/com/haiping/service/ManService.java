package com.haiping.service;

import com.haiping.dao.ManDao;
import com.haiping.domain.Man;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tony
 * @version 1.0
 * @date 2022-11-07 16:59
 */
@Service
public class ManService {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Autowired
    private ManDao manDao;

    public List<Man> findAll2(){
        ManDao mapper = sqlSessionTemplate.getMapper(ManDao.class);
        return mapper.findAll();
    }

    public List<Man> findAll(){
        return manDao.findAll();
    }

}
