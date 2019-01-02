package com.hust.o2o.model;

import java.util.Date;

/**
 * @author: wang
 * @Desciption: 微信认证实体类
 * @Date: Created in 11:28 2018/12/28
 * @Modified By:
 **/
public class WeChatAuth {

    private Long weChatAUthId;
    private String openId;
    private Date createTime;
    private Person person;

    /**
     * 返回实体ID
     * @return
     */
    public Long getWeChatAUthId() {
        return weChatAUthId;
    }

    public void setWeChatAUthId(Long weChatAUthId) {
        this.weChatAUthId = weChatAUthId;
    }

    /**
     * 返回授权ID
     * @return
     */
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 返回创建时间
     * @return
     */
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 返回绑定的用户
     * @return
     */
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
