package com.hust.o2o.utils;

/**
 * @author: wang
 * @Desciption:
 * @Date: Created in 11:17 2018/12/28
 * @Modified By:
 **/
public enum  Gender implements BaseCodeEnum {
    MALE(1,"男"),
    FEMALE(2,"女");

    private Integer genderId;
    private String genderInfo;

    Gender(Integer genderId, String genderInfo) {
        this.genderId = genderId;
        this.genderInfo = genderInfo;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public String getGenderInfo() {
        return genderInfo;
    }

    public void setGenderInfo(String genderInfo) {
        this.genderInfo = genderInfo;
    }

    @Override
    public int code() {
        return getGenderId();
    }
}
