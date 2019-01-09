package com.hust.o2o.service.impl;

import com.hust.o2o.dao.ProductCategoryDAO;
import com.hust.o2o.dto.ProductCategoryExecution;
import com.hust.o2o.enums.ProductCategoryStateEnum;
import com.hust.o2o.exceptions.ProductCategoryOperationException;
import com.hust.o2o.model.ProductCategory;
import com.hust.o2o.service.ProductCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品类别 业务逻辑操作
 *
 * @author wangleifu
 * @created 2019-01-07 14:19
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @Override
    public List<ProductCategory> queryProductCategory(long shopId) {
        return productCategoryDAO.queryProductCategory(shopId);
    }

    @Override
    @Transactional
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException {
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
                int effectedNum = productCategoryDAO.batchInsertProductCategory(productCategoryList);
                if (effectedNum <= 0) {
                    throw new ProductCategoryOperationException("商品类别信息添加失败！");
                }
                else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            } catch (Exception e) {
                logger.info("batchAddProductCategory error: " + e.getMessage());
                throw new ProductCategoryOperationException("batchAddProductCategory error: " + e.getMessage());
                //return new ProductCategoryExecution(ProductCategoryStateEnum.INNER_ERROR);
            }
        }
        else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }

    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
            throws ProductCategoryOperationException {
        // TODO 将此类别下的商品的商品ID设置为空
        try {
            int effectedNum = productCategoryDAO.deleteProductCategory(productCategoryId, shopId);
            if (effectedNum <= 0) {
                throw new ProductCategoryOperationException("商品类别删除失败！");
            }
            else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            logger.info("deleteProductCategory error: " + e.getMessage());
            throw new ProductCategoryOperationException("deleteProductCategory error: " + e.getMessage());
            //return new ProductCategoryExecution(ProductCategoryStateEnum.INNER_ERROR);
        }
    }
}
