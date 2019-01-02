package com.hust.o2o.service;

import com.hust.o2o.BaseTest;
import com.hust.o2o.model.Area;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: wang
 * @Desciption: AreaService 测试
 * @Date: Created in 15:32 2019/1/2
 * @Modified By:
 **/
public class AreaServiceTest extends BaseTest {

    @Autowired
    private AreaService areaService;

    @Test
    public void testGetAreaList(){
        List<Area> list = areaService.getAreaList();

        Assert.assertEquals(11,list.size());
    }

}
