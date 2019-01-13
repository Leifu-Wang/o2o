package com.hust.o2o.dao;

import com.hust.o2o.model.Shop;

/**
 * @author: wang
 * @Desciption: 店铺DAO层接口
 * @Date: Created in 16:16 2019/1/3
 * @Modified By:
 **/
public interface ShopDAO {

    /**
     * 新增店铺
     * @param shop
     * @return 1：插入成功，-1：插入失败
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     * @param shop
     * @return 1: 更新成功
     */
    int updateShop(Shop shop);

    /**
     * 依据店铺名称获取店铺对象
     * @param shopName
     * @return 1: 获取成功
     */
    Shop getByName(String shopName);

    /**
     * 通过店铺 ID 获取店铺对象
     * @param shopId
     * @return
     */
    Shop queryById(Long shopId);

}
