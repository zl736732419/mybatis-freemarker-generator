package com.zheng.example.filter;

import java.io.Serializable;

/**
 * User实体条件查询过滤器
 * @Author zhenglian
 * @Date 10:39 2018-05-31
 */
public class UserFilter extends AbstractFilter {
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

