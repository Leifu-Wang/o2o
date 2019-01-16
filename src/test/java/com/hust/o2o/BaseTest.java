package com.hust.o2o;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: wang
 * @Desciption: 测试父类，初始化Spring 配置
 * @Date: Created in 14:28 2019/1/2
 * @Modified By:
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class BaseTest {

    @Test
    public void separator(){
        System.out.println(System.getProperty("file.separator"));
    }
}
