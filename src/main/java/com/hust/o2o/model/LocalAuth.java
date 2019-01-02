package com.hust.o2o.model;

import java.util.Date;

/**
 * @author: wang
 * @Desciption: 本地账户认证实体类
 * @Date: Created in 11:30 2018/12/28
 * @Modified By:
 **/
public class LocalAuth {

    private Long localAuthId;
    private String username;
    private String password;
    private Date createTime;
    private Date updateTime;
    private Person person;


    /**
     * 返回本地账号ID
     *
     * @return
     */
    public Long getLocalAuthId() {
        return localAuthId;
    }

    public void setLocalAuthId(Long localAuthId) {
        this.localAuthId = localAuthId;
    }

    /**
     * 返回登陆用户名
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 返回登陆密码
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 返回创建时间
     *
     * @return
     */
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 返回最新的修改时间
     * @return
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 返回绑定的用户信息
     *
     * @return
     */
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


}
