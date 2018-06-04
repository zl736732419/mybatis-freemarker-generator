package com.zheng.generator.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 数据库枚举类型
 * 定义各种数据库类型对应的java类型
 * 这里只是列出了常用的几种类型，如果程序执行中遇到未知类型，再添加即可
 * @Author zhenglian
 * @Date 2018/6/3 23:06
 */
public enum EnumDBType {
    BIG_INT("bigint", "Long", ""),
    INT("int", "Integer", ""),
    DECIMAL("decimal", "Double", ""),
    TIMESTAMP("timestamp", "Date", ""),
    DATE_TIME("datetime", "Date", ""),
    TEXT("text", "String", ""),
    VARCHAR("varchar", "String", ""),
    TINYINT("tinyint", "byte", "")
    ;
    /**
     * 数据库类型
     */
    private String dbType;
    /**
     * java类型
     */
    private String javaType;
    /**
     * 描述
     */
    private String description;

    EnumDBType(String dbType, String javaType, String description) {
        this.dbType = dbType;
        this.javaType = javaType;
        this.description = description;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static EnumDBType findByDBType(String dbType) {
        if (StringUtils.isEmpty(dbType)) {
            return null;
        }
        for (EnumDBType type : EnumDBType.values()) {
            if (Objects.equals(dbType, type.getDbType())) {
                return type;
            }
        }
        return null;
    }

}
