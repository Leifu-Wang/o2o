package com.hust.o2o.utils;

/**
 * @author: wang
 * @Desciption:
 * @Date: Created in 11:12 2018/12/28
 * @Modified By:
 **/
public enum RoleType implements BaseCodeEnum {
    /**
     *
     */
    CUSTOM(1,"顾客"),
    /**
     *
     */
    MERCHANT(2,"商家"),
    /**
     *
     */
    ADMIN(3,"管理员");

    private Integer typeId;
    private String typeInfo;
//    private static Map<Integer, RoleType> roleTypeMap = new HashMap<>();

//    static {
//        for (RoleType roleType : RoleType.values()){
//            roleTypeMap.put(roleType.getTypeId(),roleType);
//        }
//    }

    RoleType(Integer typeId, String typeInfo){
        this.typeId = typeId;
        this.typeInfo = typeInfo;
    }

//    public static RoleType getEnumByKey(Integer typeId){
//        return roleTypeMap.get(typeId);
//    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeInfo() {
        return typeInfo;
    }

    public void setTypeInfo(String typeInfo) {
        this.typeInfo = typeInfo;
    }

    @Override
    public int code() {
        return getTypeId();
    }
}
