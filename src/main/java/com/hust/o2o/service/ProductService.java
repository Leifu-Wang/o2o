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
     *
     * @param product
     * @param thumbnail
     * @param productImgList
     * @return ProductExecution DTO数据传输对象
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
            throws ProductOperationException;

    /**
     * 通过商品Id查询商品信息
     *
     * @param productId
     * @return
     */
    Product getProductById(long productId);

    /**
     * 查询指定条件下商品列表并分页，可输入的条件有：商品名（模糊），商品状态，店铺id，商品类别
     *
     * @param product   查询条件，存储在商品类里面
     * @param pageIndex 分页，行号
     * @param pageSize  分页，页大小
     * @return
     * @throws ProductOperationException
     */
    ProductExecution getProductList(Product product, int pageIndex, int pageSize)
            throws ProductOperationException;

    /**
     * 查询指定条件下对应的商品总数
     *
     * @param product
     * @return
     */
    int getProductCount(Product product);

    /**
     * 修改商品信息，包括缩略图和详情图
     *
     * @param product
     * @param thumbnail
     * @param productImgList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
            throws ProductOperationException;

    /**
     * 删除商品
     *
     * @return
     * @throws ProductOperationException
     */
    int deleteProduct(long productId) throws ProductOperationException;
}
