package com.zheng.example.filter;

import java.io.Serializable;

/**
 * TODO
 * @Author zhenglian
 * @Date 17:54 2018/5/28
 */
public class UserFilter implements Serializable {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
