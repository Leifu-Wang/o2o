package com.hust.o2o.model;

import java.util.Date;

/**
 * @author: wang
 * @Desciption: 店铺实体类
 * @Date: Created in 13:34 2018/12/28
 * @Modified By:
 **/
public class Shop {

    private Long shopId;
    private String shopName;
    private String shopDesc;
    private String shopAddr;
    private String shopImg;
    private String phone;
    private Integer priority;
    private Date createTime;
    private Date updateTime;
    private Integer enableStatus;
    private String advice;
    private Area area;
    private ShopCategory shopCategory;
    private Person owner;

    /**
     * 获取店铺ID
     * @return
     */
    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取店铺名称
     * @return
     */
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * 获取店铺描述
     * @return
     */
    public String getShopDesc() {
        return shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    /**
     * 获取店铺地址
     * @return
     */
    public String getShopAddr() {
        return shopAddr;
    }

    public void setShopAddr(String shopAddr) {
        this.shopAddr = shopAddr;
    }

    /**
     * 获取店铺门面照地址
     * @return
     */
    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    /**
     * 获取联系电话
     * @return
     */
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取店铺权重
     * @return
     */
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 获取创建时间
     * @return
     */
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取最后修改时间
     * @return
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取店铺状态
     * -1：不可用，0：审核中，1：可用
     * @return
     */
    public Integer getEnableStatus() {
        return enableStatus;
    }
    /**
     * 设置店铺状态
     * -1：不可用，0：审核中，1：可用
     * @return
     */
    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    /**
     * 获取管理员对店铺的建议
     * @return
     */
    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    /**
     * 获取店铺所处区域
     * @return
     */
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    /**
     * 获取店铺所属类别
     * @return
     */
    public ShopCategory getShopCategory() {
        return shopCategory;
    }

    public void setShopCategory(ShopCategory shopCategory) {
        this.shopCategory = shopCategory;
    }

    /**
     * 获取店铺所属人
     * @return
     */
    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
