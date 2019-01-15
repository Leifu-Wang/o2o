package com.hust.o2o.dao;

import com.hust.o2o.model.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: wang
 * @Desciption: 店铺DAO层接口
 * @Date: Created in 16:16 2019/1/3
 * @Modified By:
 **/
public interface ShopDAO {

    /**
     * 条件搜索获取店铺列表, 条件: 区域，类别，店铺名（模糊查询），店铺状态，持有人
     * @param shopCondition 条件
     * @param rowIndex 起始行
     * @param pageSize 页面条目数目
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * 返回上述条件搜索的数据总数
     * @param shopCondition
     * @return
     */
    Integer queryShopListCount(@Param("shopCondition") Shop shopCondition);

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
