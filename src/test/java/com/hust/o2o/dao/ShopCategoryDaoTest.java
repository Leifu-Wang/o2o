package com.hust.o2o.dao;

import com.hust.o2o.BaseTest;
import com.hust.o2o.model.Shop;
import com.hust.o2o.model.ShopCategory;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: wang
 * @Desciption:
 * @Date: Created in 18:35 2019/1/6
 * @Modified By:
 **/
public class ShopCategoryDaoTest extends BaseTest {

    @Autowired
    private ShopCategoryDAO shopCategoryDAO;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Test
    public void testQueryShopCategoryCondition(){
        logger.info("---测试，QueryShopCategoryCondition---");
        ShopCategory parent = new ShopCategory();
        parent.setShopCategoryId(1l);
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setParent(parent);
        logger.info("---父类别 ：{}", shopCategory);

        List<ShopCategory> result = shopCategoryDAO.queryShopCategory(shopCategory);
        logger.info("---父类下的所有子类类别：{}---", result);

        Assert.assertEquals(5, result.size());


    }

}
