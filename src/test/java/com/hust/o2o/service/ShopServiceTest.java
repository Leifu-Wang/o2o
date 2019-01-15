package com.hust.o2o.service;

import com.hust.o2o.BaseTest;
import com.hust.o2o.dao.AreaDAO;
import com.hust.o2o.dao.AreaDaoTest;
import com.hust.o2o.dao.PersonDAO;
import com.hust.o2o.dao.ShopCategoryDAO;
import com.hust.o2o.dto.ShopExecution;
import com.hust.o2o.exceptions.ShopOperationException;
import com.hust.o2o.model.Area;
import com.hust.o2o.model.Person;
import com.hust.o2o.model.Shop;
import com.hust.o2o.model.ShopCategory;
import com.hust.o2o.utils.ShopStateEnum;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

/**
 * @author: wang
 * @Desciption:
 * @Date: Created in 15:14 2019/1/4
 * @Modified By:
 **/
public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopService;

    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private ShopCategoryDAO shopCategoryDAO;

    @Autowired
    private AreaDAO areaDAO;


    private static Logger logger = LoggerFactory.getLogger(ShopServiceTest.class.getName());

    @Test
    @Ignore
    public void testGetShopList(){

        Shop shopCondition = new Shop();
        Person owner = new Person();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        logger.info("---测试通过联合搜索店铺信息---");
        shopCondition.setOwner(owner);
        shopCondition.setArea(area);
        shopCondition.setShopCategory(shopCategory);
        owner.setUserId(4l);
        area.setAreaId(1);
        shopCategory.setShopCategoryId(7l);

        logger.info("---持有人：huangmenji, Id: {}; 区域: 东区, Id: {}; 类别: 火锅, Id: {}", owner.getUserId(), area.getAreaId(), shopCategory.getShopCategoryId());
        ShopExecution se = shopService.getShopList(shopCondition,0,1);
        Assert.assertNotEquals(ShopStateEnum.INNER_ERROR.getState(), se.getState());
        Assert.assertEquals(1, se.getShopList().size());
        Assert.assertEquals(2, se.getCount());
    }

    @Test
    @Ignore
    public void testModifyShopTest() throws ShopOperationException, FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopName("华莱士光谷店");
        shop.setShopDesc("汉堡专卖店");
        File file = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\desert.jpg");
        InputStream is = new FileInputStream(file);
        ShopExecution shopExecution = shopService.modifyShop(shop, is, "desert.jpg");
        Assert.assertEquals(shopExecution.getState(), 1);
    }

    @Test
    @Ignore
    public void addShopTest(){
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

        shop.setShopName("鲜炎炭火火锅");
        shop.setShopDesc("鲜炎炭火火锅（江汉路总店）");
        shop.setShopAddr("江汉区江汉区民生路189号");
        shop.setPhone("886886886");
        shop.setPriority(3);
        shop.setAdvice("审核中");

        File shopImg = new File("/Users/jerrywang/WebLearning/ideaWorkSpace/resources/o2o/shops/美食/ms-1.jpg");

//        ShopExecution shopExecution = shopService.addShop(shop,shopImg);
        ShopExecution shopExecution = null;
        Assert.assertEquals(shopExecution.getState(), ShopStateEnum.CHECK.getState());
    }

}
