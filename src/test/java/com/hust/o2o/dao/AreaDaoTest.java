package com.hust.o2o.dao;

import com.hust.o2o.BaseTest;
import com.hust.o2o.model.Area;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: wang
 * @Desciption: 测试 AreaDao 接口的可用性
 * @Date: Created in 14:27 2019/1/2
 * @Modified By:
 **/
public class AreaDaoTest extends BaseTest {

    @Autowired
    private AreaDAO areaDAO;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Test
    public void testList(){
        List<Area> list = areaDAO.list();
        logger.info(list.get(0).getAreaName());
        assert list.size() == 11;
    }

}
