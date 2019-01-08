package com.hust.o2o.dao;

import com.hust.o2o.BaseTest;
import com.hust.o2o.model.ProductImg;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品详情缩略图 DAO接口 可用性测试
 *
 * @author wangleifu
 * @created 2019-01-07 20:39
 */
public class ProductImgDAOTest extends BaseTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    ProductImgDAO productImgDAO;

    @Test
    public void testABatchInsertProductImg() {
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("测试图片1");
        productImg1.setImgDesc("测试图片DESC1");
        productImg1.setPriority(1);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(2L);

        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("测试图片1");
        productImg2.setImgDesc("测试图片DESC1");
        productImg2.setPriority(1);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(2L);

        List<ProductImg> productImgList = new ArrayList<>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);

        int effectedNum = productImgDAO.batchInsertProductImg(productImgList);
        logger.info("effectedNum: " + effectedNum);
        Assert.assertEquals(productImgList.size(), effectedNum);
    }
}
