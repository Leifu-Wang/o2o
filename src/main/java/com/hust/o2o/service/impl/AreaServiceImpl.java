package com.hust.o2o.service.impl;

import com.hust.o2o.dao.AreaDAO;
import com.hust.o2o.model.Area;
import com.hust.o2o.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: wang
 * @Desciption:
 * @Date: Created in 15:30 2019/1/2
 * @Modified By:
 **/
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDAO areaDAO;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Override
    public List<Area> getAreaList() {
        logger.info("获取所有区域列表");

        return areaDAO.list();
    }
}
