package com.hust.o2o.dao;

import com.hust.o2o.BaseTest;
import com.hust.o2o.model.Person;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: wang
 * @Desciption: 测试 PersonDAO 接口功能性
 * @Date: Created in 14:46 2019/1/2
 * @Modified By:
 **/
public class PersonDaoTest extends BaseTest {

    @Autowired
    private PersonDAO personDAO;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Test
    public void testList(){
        List<Person> list = personDAO.list();
        logger.info(list.get(0).getUserName());
        assertEquals(list.size(),11);
    }

}
