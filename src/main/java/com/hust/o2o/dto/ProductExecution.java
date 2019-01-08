package com.hust.o2o.dto;

import com.hust.o2o.enums.ProductStateEnum;
import com.hust.o2o.model.Product;

import java.util.List;

/**
 * 商品详情DAO操作 返回结果
 *
 * @author wangleifu
 * @created 2019-01-07 20:56
 */
public class ProductExecution {
    // 结果状态
    private int state;
    // 状态标识
    private String stateInfo;
    // 操作商品的数量
    private int count;
    // 操作的product（增删改商品的时候用）
    private Product product;
    // 操作的product列表（增删改商品的时候用）
    private List<Product> productList;

    // 失败的构造器
    public ProductExecution(ProductStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }
    // 成功的构造器
    public ProductExecution(ProductStateEnum stateEnum, Product product) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.product = product;
    }
    // 成功的构造器
    public ProductExecution(ProductStateEnum stateEnum, List<Product> productList) {
        this.productList = productList;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
