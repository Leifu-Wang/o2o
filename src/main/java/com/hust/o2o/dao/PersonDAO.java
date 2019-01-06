package com.hust.o2o.dao;

import com.hust.o2o.model.Person;

import java.util.List;

/**
 * @author: wang
 * @Desciption: 用户信息DAO接口
 * @Date: Created in 14:36 2019/1/2
 * @Modified By:
 **/
public interface PersonDAO {

    /**
     * 返回所有用户信息列表
     * @return personList
     */
    List<Person> list();

    /**
     * 根据用户名查找用户信息，测试用
     * @return
     */
    Person getByUserName(String userName);

}
