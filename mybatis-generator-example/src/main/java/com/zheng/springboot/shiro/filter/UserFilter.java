package com.zheng.springboot.shiro.filter;

import java.io.Serializable;
import java.util.Date;

/**
 * User实体条件查询过滤器
 * @Author zhenglian
 * @Date 10:30 2018-06-16
 */
public class UserFilter extends BaseFilter {
    private String username;
    private String salt;
    private String password;
    private String avatar;
    private String nickName;
    private Integer status;
    private Date activeTime;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;

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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
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

