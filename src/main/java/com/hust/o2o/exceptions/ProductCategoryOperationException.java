package com.hust.o2o.exceptions;

/**
 * 商品类别操作 异常捕获与处理
 *
 * @author wangleifu
 * @create 2019-01-07 15:52
 */
public class ProductCategoryOperationException extends RuntimeException {

    private static final long serialVersionUID = 7884457525866548615L;

    public ProductCategoryOperationException(String msg) {
        super(msg);
    }
}
