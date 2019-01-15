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

    /**
     * 根据商铺Id 获取商铺信息
     * @param shopId
     * @return
     */
    public Shop getShopById(long shopId);

    /**
     * 更新店铺信息
     * @param shop
     * @param shopInputStream
     * @param fileName
     * @return
     */
    public ShopExecution modifyShop(Shop shop, InputStream shopInputStream, String fileName);

    /**
     * 根据条件搜索获取店铺列表以及数据总数
     * @param shopCondition 搜索条件
     * @param pageIndex 页面索引 索引从 1 开始
     * @param pageSize 页面条目
     * @return
     */
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
}
