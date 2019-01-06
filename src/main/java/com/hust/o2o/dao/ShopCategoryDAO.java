package com.hust.o2o.dao;

import com.hust.o2o.model.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: wang
 * @Desciption: 店铺类别 DAO 层接口
 * @Date: Created in 16:48 2019/1/3
 * @Modified By:
 **/
public interface ShopCategoryDAO {

    /**
     * 依据类别名获取类别对象
     * @param shopCategoryName
     * @return
     */
    ShopCategory getByName(String shopCategoryName);

    /**
     * 依据条件获取类别列表
     * @param shopCategoryCondition
     * @return
     */
    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);

}
