package com.hust.o2o.service;

import com.hust.o2o.dto.ShopExecution;
import com.hust.o2o.model.Shop;

import java.io.InputStream;


/**
 * @author: wang
 * @Desciption:
 * @Date: Created in 14:51 2019/1/4
 * @Modified By:
 **/
public interface ShopService {

    /**
     * 添加店铺，并返回操作实体类
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     */
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName);

}
