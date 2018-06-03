package com.zheng.generator.domain;

/**
 * 数据库字段信息
 * @Author zhenglian
 * @Date 2018/6/3 23:05
 */
public class DBColumn {
    /**
     * 数据库字段类型
     */
    private String type;
    /**
     * 数据库字段名
     */
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
