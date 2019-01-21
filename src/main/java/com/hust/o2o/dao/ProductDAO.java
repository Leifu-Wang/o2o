package com.hust.o2o.dao;

import com.hust.o2o.model.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品详情 DAO层接口
 *
 * @author wangleifu
 * @create 2019-01-07 19:40
 */
public interface ProductDAO {

    /**
     * 插入商品
     *
     * @param product
     * @return
     */
    int insertProduct(Product product);

    /**
     * 通过商品id查找商品
     *
     * @param productId
     * @return
     */
    Product queryProductById(long productId);

    /**
     * 查询指定条件下商品列表并分页，可输入的条件有：商品名（模糊），商品状态，店铺id，商品类别
     *
     * @param productCondition 查询条件，存储在商品类里面
     * @param rowIndex         分页，行号
     * @param pageSize         分页，页大小
     * @return
     */
    List<Product> queryProductList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex,
                                   @Param("pageSize") int pageSize);

    /**
     * 查询指定条件下对应的商品总数
     *
     * @param productCondition 查询条件，存储在商品类里面
     * @return
     */
    int queryProductCount(@Param("productCondition") Product productCondition);

    /**
     * 更新商品信息
     *
     * @param product
     * @return
     */
    int updateProduct(Product product);

    /**
     * 删除商品类别的时候，将该类别的商品的类别id全部置为空
     *
     * @param productCategoryId 指定删除的商品类别
     * @return
     */
    int updateProductCategoryToNull(long productCategoryId);

    /**
     * 删除商品
     *
     * @param productId
     * @return
     */
    int deleteProduct(long productId);

}
