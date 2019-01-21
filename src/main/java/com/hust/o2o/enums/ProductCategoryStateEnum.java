package com.hust.o2o.enums;

/**
 * 商品类别操作 状态枚举
 *
 * @author wangleifu
 * @create 2019-01-07 15:05
 */
public enum ProductCategoryStateEnum {
    SUCCESS(1, "创建成功"), INNER_ERROR(-1001, "操作失败"), EMPTY_LIST(-1002, "添加数少于1");

    private Integer state;
    private String stateInfo;

    ProductCategoryStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return this.state;
    }

    public String getStateInfo() {
        return this.stateInfo;
    }

    public ProductCategoryStateEnum stateOf(int index) {
        for (ProductCategoryStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
