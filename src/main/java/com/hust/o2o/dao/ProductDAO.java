package com.hust.o2o.dao;

import com.hust.o2o.model.Product;

import java.util.List;

/**
 * 商品详情 DAO层接口
 *
 * @author wangleifu
 * @create 2019-01-07 19:40
 */
public interface ProductDAO {

    List<Product> queryProductList();

    int insertProduct(Product product);
}
