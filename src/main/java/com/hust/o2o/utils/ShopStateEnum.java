package com.hust.o2o.utils;

import com.hust.o2o.model.Shop;

/**
 * @author: wang
 * @Desciption:
 * @Date: Created in 14:25 2019/1/4
 * @Modified By:
 **/
public enum  ShopStateEnum {

    CHECK(0, "审核中"),
    OFFLINE(-1, "非法店铺"),
    SUCCESS(1, "操作成功"),
    PASS(2, "审核通过"),
    INNER_ERROR(-1001, "内部系统错误"),
    NULL_SHOP(-1002, "店铺为空");

    private int state;
    private String stateInfo;

    ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    /**
     * 依据状态标识返回枚举对象
     * @param state
     * @return
     */
    public static ShopStateEnum stateOf(int state){
        for (ShopStateEnum shopStateEnum : values()){
            if (shopStateEnum.getState() == state)
                return shopStateEnum;
        }
        return null;
    }


}
