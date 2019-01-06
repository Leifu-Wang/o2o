package com.hust.o2o.model;

import java.util.Date;

/**
 * @author: wang
 * @Desciption: 店铺类别实体类
 * @Date: Created in 13:25 2018/12/28
 * @Modified By:
 **/
public class ShopCategory {

    private Long shopCategoryId;
    private String shopCategoryName;
    private String shopCategoryDesc;
    private String shopCategoryImg;
    private Integer priority;
    private Date createTime;
    private Date updateTime;
    private ShopCategory parent;

    /**
     * 获取类别ID
     * @return
     */
    public Long getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(Long shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    /**
     * 获取类别名
     * @return
     */
    public String getShopCategoryName() {
        return shopCategoryName;
    }

    public void setShopCategoryName(String shopCategoryName) {
        this.shopCategoryName = shopCategoryName;
    }

    /**
     * 获取类别描述
     * @return
     */
    public String getShopCategoryDesc() {
        return shopCategoryDesc;
    }

    public void setShopCategoryDesc(String shopCategoryDesc) {
        this.shopCategoryDesc = shopCategoryDesc;
    }

    /**
     * 获取类别图片地址
     * @return
     */
    public String getShopCategoryImg() {
        return shopCategoryImg;
    }

    public void setShopCategoryImg(String shopCategoryImg) {
        this.shopCategoryImg = shopCategoryImg;
    }

    /**
     * 获取类别权重
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
     * 获取父类别
     * @return
     */
    public ShopCategory getParent() {
        return parent;
    }

    public void setParent(ShopCategory parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "ShopCategory{" +
                "shopCategoryId=" + shopCategoryId +
                ", shopCategoryName='" + shopCategoryName + '\'' +
                ", shopCategoryDesc='" + shopCategoryDesc + '\'' +
                ", shopCategoryImg='" + shopCategoryImg + '\'' +
                ", priority=" + priority +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", parent=" + parent +
                '}';
    }
}
