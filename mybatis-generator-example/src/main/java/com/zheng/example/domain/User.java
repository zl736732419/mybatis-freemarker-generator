package com.zheng.example.domain;

/**
 * @Author zhenglian
 * @Date 17:51 2018/5/28
 */
public class User {
    private Integer id;
    private String name;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
