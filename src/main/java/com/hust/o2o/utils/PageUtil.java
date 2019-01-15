package com.hust.o2o.utils;

public class PageUtil {

    /**
     * 根据当前页面以及页面条目数获取当前页面起始行
     * @param pageIndex 页面索引, 索引从 1 开始
     * @param pageSize 页面条目
     * @return
     */
    public static int calculateRowIndex(int pageIndex, int pageSize) {
        return pageIndex > 0 ? (pageIndex - 1) * pageSize : 0;
    }

}
