package com.zheng.example.filter;

import java.io.Serializable;

/**
 * User实体条件查询过滤器
 * @Author zhenglian
 * @Date 15:10 2018-05-31
 */
public class UserFilter extends AbstractFilter {
    private String name;
    private Integer isDelete;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}

