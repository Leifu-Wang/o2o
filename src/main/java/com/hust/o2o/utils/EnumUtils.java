package com.hust.o2o.utils;

/**
 * @author: wang
 * @Desciption: 依据数据库存储的值获取枚举对象
 * @Date: Created in 15:22 2019/1/2
 * @Modified By:
 **/
public class EnumUtils {

    /**
     * 获取对应的枚举类型
     * @param enumClass
     * @param code
     * @param <E>
     * @return
     */
    public static <E extends Enum<?> & BaseCodeEnum> E codeOf(Class<E> enumClass, int code){
        E[] enumConstant = enumClass.getEnumConstants();
        for (E e : enumConstant){
            if (e.code() == code)
                return e;
        }
        return null;
    }

}
