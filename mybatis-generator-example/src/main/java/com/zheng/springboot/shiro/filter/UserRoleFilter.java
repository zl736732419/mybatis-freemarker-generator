package com.zheng.springboot.shiro.filter;

import java.io.Serializable;
import java.util.Date;

/**
 * UserRole实体条件查询过滤器
 * @Author zhenglian
 * @Date 10:30 2018-06-16
 */
public class UserRoleFilter extends BaseFilter {
    private Integer userId;
    private Integer roleId;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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

