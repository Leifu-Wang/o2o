package com.hust.o2o.utils;

import com.sun.tools.javah.Gen;

import java.util.HashMap;
import java.util.Map;

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
//    private static Map<Integer,Gender> genderMap = new HashMap<>();

//    static {
//        for (Gender gender : Gender.values()){
//            genderMap.put(gender.genderId,gender);
//        }
//    }

    Gender(Integer genderId, String genderInfo) {
        this.genderId = genderId;
        this.genderInfo = genderInfo;
    }
//
//    public static Gender getEnumByKey(Integer genderId){
//        return genderMap.get(genderId);
//    }

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
