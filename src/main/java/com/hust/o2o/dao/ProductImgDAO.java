package com.hust.o2o.dao;

import com.hust.o2o.model.ProductImg;

import java.util.List;

/**
 * 商品详情图 DAO层接口
 *
 * @author wangleifu
 * @create 2019-01-07 19:46
 */
public interface ProductImgDAO {

    /**
     * 批量插入商品缩略图
     *
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     * 删除指定商品的详情图
     *
     * @param productId
     * @return
     */
    int deleteProductImgByProductId(long productId);

    List<ProductImg> queryProductImgList(long productId);
}
