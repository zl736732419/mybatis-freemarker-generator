package com.zheng.generator.domain.db;

/**
 * 数据库字段信息
 * @Author zhenglian
 * @Date 2018/6/3 23:05
 */
public class DBColumn {
    /**
     * 数据库字段类型
     */
    private String dbType;
    /**
     * 对应的java类型
     */
    private String javaType;
    /**
     * 数据库字段名
     */
    private String name;

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    @Override
    public String toString() {
        return "DBColumn{" +
                "dbType='" + dbType + '\'' +
                ",javaType='" + javaType + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
