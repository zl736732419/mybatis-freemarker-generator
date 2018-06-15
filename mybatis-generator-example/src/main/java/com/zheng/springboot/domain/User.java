package com.zheng.springboot.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体
 * @Author zhenglian
 * @Date 15:03 2018/6/15
 */
public class User implements Serializable {
    /**
     * 标识
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码盐，用于加密密码
     */
    private String salt;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户头像(这里准备使用ali云图像存储)
     */
    private String avator;
    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 状态
     * @see com.zheng.springboot.enums.EnumUserStatus
     */
    private Integer status;
    /**
     * 用户处于锁定状态被激活的时间
     */
    private Date activeTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 是否删除
     */
    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
