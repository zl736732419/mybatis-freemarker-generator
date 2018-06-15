package com.zheng.springboot.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 * @Author zhenglian
 * @Date 15:11 2018/6/15
 */
public class Role implements Serializable {
    /**
     * 标识
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色头像
     */
    private String avator;

    /**
     * 角色状态
     * @see com.zheng.springboot.enums.EnumRoleStatus
     */
    private Integer status;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
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
