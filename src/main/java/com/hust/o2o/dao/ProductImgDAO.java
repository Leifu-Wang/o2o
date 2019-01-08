package com.hust.o2o.dao;

import com.hust.o2o.model.ProductImg;

import java.util.List;

/**
 * 商品简略图 DAO层接口
 *
 * @author wangleifu
 * @create 2019-01-07 19:46
 */
public interface ProductImgDAO {

    /**
     * 批量插入商品缩略图
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);
}
