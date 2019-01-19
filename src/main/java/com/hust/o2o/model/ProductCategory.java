package com.hust.o2o.model;

import java.util.Date;

/**
 * @author: wang
 * @Desciption: 商品类别
 * @Date: Created in 13:48 2018/12/28
 * @Modified By:
 **/
public class ProductCategory {

    private Long productCategoryId;
    private Long shopId;
    private String productCategoryName;
    private Integer priority;
    private Date createTime;

    /**
     * 获取商品类别ID
     * @return
     */
    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    /**
     * 获取所属店铺ID
     * @return
     */
    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取商品类别名称
     * @return
     */
    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    /**
     * 获取商品类别权重
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
}
