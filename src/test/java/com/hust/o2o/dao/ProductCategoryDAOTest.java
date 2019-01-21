package com.hust.o2o.dao;

import com.hust.o2o.BaseTest;
import com.hust.o2o.model.ProductCategory;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试 ProductCategoryDAO 接口功能可用性
 *
 * @author wangleifu
 * @create 2019-01-07 13:33
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDAOTest extends BaseTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @Test
    public void testBQueryProductCategory()
    {
        long shopId = 1;
        List<ProductCategory> productCategoryList = productCategoryDAO.queryProductCategory(shopId);
        logger.info("该商铺自定义商品类别数为: " + productCategoryList.size());
    }

    @Test
    public void testABatchInsertProductCategory()
    {
        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setProductCategoryName("商品类别1");
        productCategory1.setShopId(1L);
        productCategory1.setPriority(1);
        productCategory1.setCreateTime(new Date());

        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("商品类别2");
        productCategory2.setShopId(1L);
        productCategory2.setPriority(2);
        productCategory2.setCreateTime(new Date());

        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(productCategory1);
        productCategoryList.add(productCategory2);

        int effectedNum = productCategoryDAO.batchInsertProductCategory(productCategoryList);
        logger.info("effectedNum: " + effectedNum);
        Assert.assertEquals(2, effectedNum);
    }

    @Test
    public void testCDeleteProductCategory() {
        long shopId = 1;
        List<ProductCategory> productCategoryList = productCategoryDAO.queryProductCategory(shopId);
        for (ProductCategory productCategory : productCategoryList) {
            if ("商品类别1".equals(productCategory.getProductCategoryName()) ||
                    "商品类别2".equals(productCategory.getProductCategoryName())) {
                int effectedNum = productCategoryDAO.deleteProductCategory(productCategory.getProductCategoryId(), shopId);
                logger.info("effectedNum: " + effectedNum);
                Assert.assertEquals(1, effectedNum);
            }
        }
    }
}
