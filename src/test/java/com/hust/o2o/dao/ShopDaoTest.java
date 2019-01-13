package com.hust.o2o.dao;

import com.hust.o2o.BaseTest;
import com.hust.o2o.model.Area;
import com.hust.o2o.model.Person;
import com.hust.o2o.model.Shop;
import com.hust.o2o.model.ShopCategory;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author: wang
 * @Desciption:
 * @Date: Created in 16:41 2019/1/3
 * @Modified By:
 **/
public class ShopDaoTest extends BaseTest {

    @Autowired
    private ShopDAO shopDAO;

    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private ShopCategoryDAO shopCategoryDAO;

    @Autowired
    private AreaDAO areaDAO;

    private Logger logger = LoggerFactory.getLogger(ShopDaoTest.class.getName());

    @Test
    @Ignore
    public void testInsertShop() {
        logger.info("---测试插入店铺---");

        Shop shop = new Shop();
        Person owner = personDAO.getByUserName("huangmenji");
        logger.info("获取店铺持有人---{}", owner == null ? null : owner.getUserName());
        ShopCategory shopCategory = shopCategoryDAO.getByName("中餐");
        logger.info("获取店铺所属类别---{}", shopCategory == null ? null : shopCategory.getShopCategoryName());
        Area area = areaDAO.getByName("东区");
        logger.info("获取店铺所属区域---{}",area == null ? null : area.getAreaName());
        shop.setOwner(owner);
        shop.setShopCategory(shopCategory);
        shop.setArea(area);

        shop.setShopName("膳当家");
        shop.setShopDesc("膳当家黄焖鸡米饭");
        shop.setShopAddr("南昌大学后街");
        shop.setPhone("886886886");
        shop.setShopImg("");
        shop.setPriority(3);
        shop.setAdvice("开门大吉");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);

        int ret = shopDAO.insertShop(shop);
        Assert.assertEquals(1,ret);

    }

    @Test
    @Ignore
    public void testUpdateShop(){
        logger.info("---测试更改店铺信息---");

        Shop shop = shopDAO.getByName("膳当家");
        logger.info("获取店铺对象------{}", shop == null ? null : shop.getShopName());

        shop.setUpdateTime(new Date());
        shop.setShopAddr("我也不知道去哪了");
        shop.setAdvice("回来吧");

        int ret = shopDAO.updateShop(shop);
        Assert.assertEquals(1,ret);
    }

    @Test
    public void testQueryShopById(){
        logger.info("---测试通过 Id 获取店铺信息---");

        Shop shop = shopDAO.queryById(1l);
        logger.info("---获取店铺对象---{}", shop == null ? null : shop.getShopName());

    }

}
