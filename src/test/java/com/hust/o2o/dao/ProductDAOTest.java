package com.hust.o2o.dao;

import com.hust.o2o.BaseTest;
import com.hust.o2o.model.Product;
import com.hust.o2o.model.ProductCategory;
import com.hust.o2o.model.Shop;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * 商品详情 DAO接口 测试
 *
 * @author wangleifu
 * @created 2019-01-07 20:04
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDAOTest extends BaseTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private long shopId = 10L;
    private long productCategoryId = 1L;

    @Autowired
    ProductDAO productDAO;

    @Test
    public void testAInsertProduct() {
        Shop shop1 = new Shop();
        shop1.setShopId(shopId);

        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(productCategoryId);


        Product product1 = new Product();
        product1.setProductName("测试1");
        product1.setProductDesc("测试1 DESC");
        product1.setImgAddr("test1");
        product1.setPriority(1);
        product1.setEnableStatus(1);
        product1.setCreateTime(new Date());
        product1.setUpdateTime(new Date());
        product1.setShop(shop1);
        product1.setProductCategory(pc);

        Product product2 = new Product();
        product2.setProductName("测试2");
        product2.setProductDesc("测试2 DESC");
        product2.setImgAddr("test2");
        product2.setPriority(1);
        product2.setEnableStatus(1);
        product2.setCreateTime(new Date());
        product2.setUpdateTime(new Date());
        product2.setShop(shop1);
        product2.setProductCategory(pc);

        Product product3 = new Product();
        product3.setProductName("test3");
        product3.setProductDesc("test3 DESC");
        product3.setImgAddr("测试3");
        product3.setPriority(1);
        product3.setEnableStatus(1);
        product3.setCreateTime(new Date());
        product3.setUpdateTime(new Date());
        product3.setShop(shop1);
        product3.setProductCategory(pc);

        int effectedNum = productDAO.insertProduct(product1);
        logger.info("effect number: " + effectedNum);
        Assert.assertEquals(1, effectedNum);

        effectedNum = productDAO.insertProduct(product2);
        logger.info("effect number: " + effectedNum);
        Assert.assertEquals(1, effectedNum);

        effectedNum = productDAO.insertProduct(product3);
        logger.info("effect number: " + effectedNum);
        Assert.assertEquals(1, effectedNum);


    }

    @Test
    public void testBQueryProductListAndCount() {
        Shop shop = new Shop();
        shop.setShopId(shopId);

        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(productCategoryId);


        Product product = new Product();
        product.setProductName("测试");

        product.setEnableStatus(1);

        product.setShop(shop);

        product.setProductCategory(pc);

        List<Product> productList = productDAO.queryProductList(product, 0, 100);
        int count = productDAO.queryProductCount(product);

        logger.info("查询商品列表数量：" + productList.size());
        Assert.assertEquals(count, productList.size());
    }

    @Test
    public void testCUpdateProductCategoryToNull() {
        int effectedNum = productDAO.updateProductCategoryToNull(productCategoryId + 1);
        Assert.assertEquals(1, effectedNum);
    }

}
