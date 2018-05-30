package com.zheng.example.domain;

import java.io.Serializable;

/**
 * @Author zhenglian
 * @Date 2018/5/30 23:03
 */
public class Teacher implements Serializable {
    private Integer id;
    private String name;

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
}
