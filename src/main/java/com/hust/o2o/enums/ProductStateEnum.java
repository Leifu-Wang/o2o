package com.hust.o2o.enums;

/**
 * 商品详情操作的状态
 *
 * @author wangleifu
 * @create 2019-01-07 21:02
 */
public enum ProductStateEnum {
    SUCCESS(1, "创建成功"), INNER_ERROR(-1001, "操作失败"), EMPTY(-1002, "添加数少于1");

    private Integer state;
    private String stateInfo;

    ProductStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}
