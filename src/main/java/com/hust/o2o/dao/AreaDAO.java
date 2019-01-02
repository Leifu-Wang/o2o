package com.hust.o2o.dao;

import com.hust.o2o.model.Area;

import java.util.List;

/**
 * @author: wang
 * @Desciption: 区域 DAO 接口
 * @Date: Created in 14:13 2019/1/2
 * @Modified By:
 **/
public interface AreaDAO {

    /**
     * 返回所有的区域列表
     * @return areaList
     */
    List<Area> list();

}
