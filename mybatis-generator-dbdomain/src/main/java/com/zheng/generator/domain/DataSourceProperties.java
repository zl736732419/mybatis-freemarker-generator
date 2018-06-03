package com.zheng.generator.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author zhenglian
 * @Date 2018/6/3 22:45
 */
@Component
@ConfigurationProperties(prefix = "domain.db")
public class DataSourceProperties {
    /**
     * 连接url
     */
    private String url;
    /**
     * 数据库驱动名
     */
    private String driverClassName;
    /**
     * 登录用户名
     */
    private String user;
    /**
     * 登录用户密码
     */
    private String password;
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
