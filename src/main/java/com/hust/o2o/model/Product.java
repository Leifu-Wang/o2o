package com.hust.o2o.model;

import java.util.Date;
import java.util.List;

/**
 * @author: wang
 * @Desciption: 商品详细信息实体类
 * @Date: Created in 11:05 2019/1/2
 * @Modified By:
 **/
public class Product {

    private Long productId;
    private String productName;
    private String productDesc;

    /**
     * 浏览 缩略图
     */
    private String imgAddr;
    private String normalPrice;
    private String promotionPrice;
    private Integer priority;
    private Date createTime;
    private Date updateTime;
    /**
     * 0: 下架
     * 1: 在前端展示系统展示
     */
    private Integer enableStatus;

    /**
     * 点击 详情图
     */
    private List<ProductImg> productImgList;
    private ProductCategory productCategory;
    private Shop shop;

    /**
     * 获取商品ID
     * @return
     */
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取商品名称
     * @return
     */
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取商品描述
     * @return
     */
    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    /**
     * 获取商品缩略图地址
     * @return
     */
    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    /**
     * 获取商品原价
     * @return
     */
    public String getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(String normalPrice) {
        this.normalPrice = normalPrice;
    }

    /**
     * 获取商品折扣价
     * @return
     */
    public String getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    /**
     * 获取商品权重
     * @return
     */
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 获取商品创建时间
     * @return
     */
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取商品修改的最新时间
     * @return
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取商品的状态。-1：不可用，0：下架，1：展示
     * @return
     */
    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    /**
     * 获取商品详情图片列表
     * @return
     */
    public List<ProductImg> getProductImgList() {
        return productImgList;
    }

    public void setProductImgList(List<ProductImg> productImgList) {
        this.productImgList = productImgList;
    }

    /**
     * 获取商品所属种类
     * @return
     */
    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    /**
     * 获取商品所属店铺
     * @return
     */
    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
