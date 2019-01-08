package com.hust.o2o.service;

import com.hust.o2o.dto.ImageHolder;
import com.hust.o2o.dto.ProductExecution;
import com.hust.o2o.exceptions.ProductOperationException;
import com.hust.o2o.model.Product;

import java.util.List;

/**
 * 商品详情 业务接口
 *
 * @author wangleifu
 * @create 2019-01-07 20:53
 */
public interface ProductService {
    /**
     * 添加商品信息、缩略图、详情图
     * @param product
     * @param thumbnail
     * @param productImgList
     * @return ProductExecution DTO数据传输对象
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
            throws ProductOperationException;
}
