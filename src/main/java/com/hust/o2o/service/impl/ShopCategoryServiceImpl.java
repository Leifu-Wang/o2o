package com.hust.o2o.service.impl;

import com.hust.o2o.dao.ShopCategoryDAO;
import com.hust.o2o.model.ShopCategory;
import com.hust.o2o.service.ShopCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Autowired
    private ShopCategoryDAO shopCategoryDAO;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public List<ShopCategory> getShopCategoryList() {
        logger.info("---获取所有店铺类别列表---");

        return shopCategoryDAO.queryShopCategory(null);
    }
}
