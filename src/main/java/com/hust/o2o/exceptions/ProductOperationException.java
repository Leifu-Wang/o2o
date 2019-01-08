package com.hust.o2o.exceptions;

/**
 * 商品详情操作 异常捕获与处理
 *
 * @author wangleifu
 * @created 2019-01-07 20:54
 */
public class ProductOperationException extends RuntimeException {

    private static final long serialVersionUID = 604197171890737373L;

    public ProductOperationException(String msg) {
        super(msg);
    }
}
