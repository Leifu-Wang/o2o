package com.hust.o2o.model;

import com.hust.o2o.utils.Gender;
import com.hust.o2o.utils.RoleType;

import java.util.Date;

/**
 * @author: wang
 * @Desciption:
 * @Date: Created in 11:09 2018/12/28
 * @Modified By:
 **/
public class Person {

    private Long userId;
    private String userName;
    private String profileImg;
    private String email;
    private Gender gender;
    private Integer enableStatus;
    private RoleType roleType;
    private Date createTime;
    private Date updateTime;

    /**
     * 获取用户id
     * @return
     */
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名
     * @return
     */
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取用户头像地址
     * @return
     */
    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    /**
     * 获取用户邮箱
     * @return
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取用户性别
     * @return
     */
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * 获取用户状态
     * @return
     */
    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    /**
     * 获取用户类型
     * @return
     */
    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    /**
     * 获取用户创建时间
     * @return
     */
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取用户最后更新时间
     * @return
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
