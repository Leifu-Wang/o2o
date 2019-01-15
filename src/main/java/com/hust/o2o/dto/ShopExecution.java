package com.hust.o2o.dto;

import com.hust.o2o.model.Shop;
import com.hust.o2o.utils.ShopStateEnum;

import java.util.List;

/**
 * @author: wang
 * @Desciption: 店铺操作状态实体类
 * @Date: Created in 14:21 2019/1/4
 * @Modified By:
 **/
public class ShopExecution {

    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 店铺数量
    private int count;

    // 操作的shop对象
    private Shop shop;

    // 操作的 shop 列表
    private List<Shop> shopList;

    public ShopExecution() {
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }

    /**
     * 店铺操作失败的构造器
     * @param shopStateEnum
     */
    public ShopExecution(ShopStateEnum shopStateEnum){
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
    }

    /**
     * 操作单个店铺对象成功的构造器
     * @param shopStateEnum
     * @param shop
     */
    public ShopExecution(ShopStateEnum shopStateEnum, Shop shop){
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
        this.shop = shop;
    }

    /**
     * 操作店铺列表成功时使用的构造器
     * @param shopStateEnum
     * @param shopList
     */
    public ShopExecution(ShopStateEnum shopStateEnum, List<Shop> shopList){
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
        this.shopList = shopList;
    }
}
