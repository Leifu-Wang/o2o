package com.hust.o2o.dto;

import com.hust.o2o.enums.ProductCategoryStateEnum;
import com.hust.o2o.model.ProductCategory;

import java.util.List;

/**
 * 商品类别操作 执行结构的结构体
 *
 * @author wangleifu
 * @create 2019-01-07 15:43
 */
public class ProductCategoryExecution {
    // 结果状态
    private int state;
    // 状态标识
    private String stateInfo;

    private List<ProductCategory> productCategorieList;

    /**
     * 操作失败时候使用的构造器
     * @param stateEnum
     */
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    /**
     * 操作成功时候使用的构造器
     * @param stateEnum
     * @param productCategorieList
     */
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum, List<ProductCategory> productCategorieList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productCategorieList = productCategorieList;
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

    public List<ProductCategory> getProductCategorieList() {
        return productCategorieList;
    }

    public void setProductCategorieList(List<ProductCategory> productCategorieList) {
        this.productCategorieList = productCategorieList;
    }
}
