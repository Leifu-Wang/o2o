package com.hust.o2o.dao;

import com.hust.o2o.model.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品类别 DAO层接口
 *
 * @author wangleifu
 * @create 2019-01-07 13:06
 */
public interface ProductCategoryDAO {
//    String TABLE_NAME = " tb_product_category ";
//    String INSERT_FIELDS = " product_category_name, priority, create_time, shop_id ";
//    String SELECT_FIELDS = " product_category_id, " + INSERT_FIELDS;

    /**
     * 通过 shopId 查询店铺商品类别
     *
     * @param shopId 店铺id标识
     * @return 当前店铺商品列表
     */
    List<ProductCategory> queryProductCategory(long shopId);

    /**
     * 批量新增商品类别
     *
     * @param productCategoryList 商品类别列表
     * @return 影响数据库的行数
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    /**
     * 删除指定店铺下的指定商品
     *
     * @param productCategoryId 商品ID
     * @param shopId            店铺ID
     * @return effect number
     */
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}
