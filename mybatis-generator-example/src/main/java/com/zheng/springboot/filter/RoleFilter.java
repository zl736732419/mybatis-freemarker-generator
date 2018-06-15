package com.zheng.springboot.filter;

import java.io.Serializable;
import java.util.Date;

/**
 * Role实体条件查询过滤器
 * @Author zhenglian
 * @Date 16:30 2018-06-15
 */
public class RoleFilter extends BaseFilter {
    private String name;
    private String avatar;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
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

