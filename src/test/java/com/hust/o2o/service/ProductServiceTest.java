package com.hust.o2o.service;

import com.hust.o2o.BaseTest;
import com.hust.o2o.dto.ImageHolder;
import com.hust.o2o.dto.ProductExecution;
import com.hust.o2o.enums.ProductStateEnum;
import com.hust.o2o.exceptions.ProductOperationException;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品模块 服务接口测试
 *
 * @author wangleifu
 * @created 2019-01-08 13:24
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductServiceTest extends BaseTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private ProductService productService;

    @Test
    public void testAAddProduct() throws FileNotFoundException {
        // 创建shopId为1并且productCategory为1的商品实例 并赋初值
        Product product = new Product();

        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(1L);

        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("测试商品1");
        product.setProductDesc("测试商品1");
        product.setCreateTime(new Date());
        product.setPriority(20);
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());

        // 创建缩略图文件流
        File thumbnailFile = new File("C:/Users/Public/Pictures/Sample Pictures/Desert.jpg");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder imageHolder = new ImageHolder(thumbnailFile.getName(), is);

        // 创建商品详情图片列表
        File productImg1 = new File("C:/Users/Public/Pictures/Sample Pictures/Desert.jpg");
        InputStream is1 = new FileInputStream(productImg1);
        File productImg2 = new File("C:/Users/Public/Pictures/Sample Pictures/Lighthouse.jpg");
        InputStream is2 = new FileInputStream(productImg2);
        File productImg3 = new File("C:/Users/Public/Pictures/Sample Pictures/Koala.jpg");
        InputStream is3 = new FileInputStream(productImg3);

        List<ImageHolder> productImgList = new ArrayList<>();
        productImgList.add(new ImageHolder(productImg1.getName(), is1));
        productImgList.add(new ImageHolder(productImg2.getName(), is2));
        productImgList.add(new ImageHolder(productImg3.getName(), is3));

        // 添加进数据库
        ProductExecution pe = productService.addProduct(product, imageHolder, productImgList);
        logger.info("ProductExecution state: " + pe.getState() + " stateInfo: " + pe.getStateInfo());
        Assert.assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
    }
}
