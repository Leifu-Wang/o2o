package com.hust.o2o.service.impl;

import com.hust.o2o.dao.ShopDAO;
import com.hust.o2o.dto.ShopExecution;
import com.hust.o2o.exceptions.ShopOperationException;
import com.hust.o2o.model.Shop;
import com.hust.o2o.service.ShopService;
import com.hust.o2o.utils.ImageUtil;
import com.hust.o2o.utils.PageUtil;
import com.hust.o2o.utils.PathUtils;
import com.hust.o2o.utils.ShopStateEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author: wang
 * @Desciption:
 * @Date: Created in 14:52 2019/1/4
 * @Modified By:
 **/
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDAO shopDAO;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {
        logger.info("---Service shopService addShop---");
        logger.debug("---入参 shop:{}, shopImgInputStream:{}, fileName:{}", shop, shopImgInputStream, fileName);
        // 空值判断
        if (shop == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try{
            // 初始化店铺初值
            shop.setEnableStatus(ShopStateEnum.CHECK.getState());
            shop.setCreateTime(new Date());
            shop.setUpdateTime(new Date());
            // 添加店铺信息
            int effectNum = shopDAO.insertShop(shop);
            if (effectNum <= 0){
                logger.info("---数据库添加店铺信息失败---");
                throw new ShopOperationException("店铺创建失败");
            }else {
                if (shopImgInputStream != null){
                    //存储图片
                    try{
                        addShopImg(shop, shopImgInputStream, fileName);
                    }catch (Exception e){
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }
                    // 更新店铺缩略图地址
                    effectNum = shopDAO.updateShop(shop);

                    if (effectNum <= 0){
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }
        }catch (Exception e){
            throw new ShopOperationException("addShop error: " + e.getMessage());
        }

        return new ShopExecution(ShopStateEnum.CHECK);
    }

    @Override
    public Shop getShopById(long shopId) {
        logger.info("---shopService getShopById {}", shopId);
        return shopDAO.queryById(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, InputStream shopInputStream, String fileName) {
        logger.info("---modifyShop---");
        // 1. 对象判空
        if (shop == null || shop.getShopId() == null){
            logger.info("---待修改店铺信息为空---");
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            // 2. 图片流判空并更新图片
            if (shopInputStream != null && fileName != null && !"".equals(fileName)){
                logger.info("---更新店铺缩略图---");
                Shop tempShop = getShopById(shop.getShopId());
                if (tempShop != null && tempShop.getShopImg() != null){
                    ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                }
                addShopImg(shop, shopInputStream, fileName);
            }
            // 3. 更新店铺信息
            shop.setUpdateTime(new Date());
            logger.info("---更新店铺修改时间: {}", shop.getUpdateTime());
            int effectedNum = shopDAO.updateShop(shop);
            if (effectedNum <= 0){
                logger.info("---店铺更新失败---");
                return new ShopExecution(ShopStateEnum.INNER_ERROR);
            }else {
                logger.info("---店铺更新成功---");
                return new ShopExecution(ShopStateEnum.SUCCESS);
            }
        }catch (Exception e){
            logger.info("---店铺更新失败---");
            throw new ShopOperationException("modifyShop error: " + e.getMessage());
        }
    }

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageUtil.calculateRowIndex(pageIndex, pageSize);
        List<Shop> shopList = shopDAO.queryShopList(shopCondition, rowIndex, pageSize);
        int count = shopDAO.queryShopListCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if (shopList != null){
            se.setShopList(shopList);
            se.setCount(count);
        }else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }

    /**
     * 上传图片到本地服务器中，更新 shop 的img 属性
     * @param shop
     * @param shopImgInputStream
     */
    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {

        String dest = PathUtils.getShopImagePath(shop.getShopId());
        String shopImageAddr = ImageUtil.generateThumbnail(shopImgInputStream, dest, fileName);
        shop.setShopImg(shopImageAddr);

    }
}
