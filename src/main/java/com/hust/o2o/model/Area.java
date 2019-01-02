package com.hust.o2o.model;

import java.util.Date;

/**
 * @author: wang
 * @Desciption: 区域实体信息
 * @Date: Created in 10:45 2018/12/28
 * @Modified By:
 **/
public class Area {

    private Integer areaId;
    private String areaName;
    private Integer areaPriority;
    private Date createTime;
    private Date updateTime;

    /**
     * 获取区域ID
     * @return
     */
    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * 获取区域名
     * @return
     */
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * 获取区域权重
     * @return
     */
    public Integer getAreaPriority() {
        return areaPriority;
    }

    public void setAreaPriority(Integer areaPriority) {
        this.areaPriority = areaPriority;
    }

    /**
     * 获取区域创建时间
     * @return
     */
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取区域最后更新时间
     * @return
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
