package com.zheng.springboot.shiro.filter;

import java.io.Serializable;
import java.util.Date;

/**
 * UrlResource实体条件查询过滤器
 * @Author zhenglian
 * @Date 10:30 2018-06-16
 */
public class UrlResourceFilter extends BaseFilter {
    private String name;
    private String url;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
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

