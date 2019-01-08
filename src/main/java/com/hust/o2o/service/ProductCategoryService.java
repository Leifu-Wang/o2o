package com.hust.o2o.service;

import com.hust.o2o.dto.ProductCategoryExecution;
import com.hust.o2o.exceptions.ProductCategoryOperationException;
import com.hust.o2o.model.ProductCategory;

import java.util.List;

/**
 * 商品类别 业务逻辑接口
 *
 * @author wangleifu
 * @create 2019-01-07 14:15
 */
public interface ProductCategoryService {

    /**
     * 查询指定某个点铺下的所有商品类别信息
     * @param shopId 指定的店铺ID
     * @return List<ProductCategory>
     */
    List<ProductCategory> queryProductCategory(long shopId);

    /**
     * 批量插入商品类别信息
     * @param productCategoryList 需要插入的商品类别信息列表
     * @return ProductCategoryExecution
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException;

    /**
     * 先将此类别下的商品的商品ID设置为空，再删除该类别
     * @param productCategoryId
     * @param shopId
     * @return ProductCategoryExecution
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
            throws ProductCategoryOperationException;

}
