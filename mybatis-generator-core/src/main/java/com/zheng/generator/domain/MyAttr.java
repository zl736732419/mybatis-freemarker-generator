package com.zheng.generator.domain;

/**
 * 属性
 * @Author zhenglian
 * @Date 9:39 2018/5/31
 */
public class MyAttr {

    /**
     * 属性名
     */
    private String attrName;
    /**
     * 属性类型
     */
    private String attrType;

    /**
     * 数据库对应字段名
     */
    private String dbFieldName;

    private boolean id = false;

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getDbFieldName() {
        return dbFieldName;
    }

    public void setDbFieldName(String dbFieldName) {
        this.dbFieldName = dbFieldName;
    }

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MyAttr{" +
                "id='" + id + '\'' +
                "attrName='" + attrName + '\'' +
                ", attrType='" + attrType + '\'' +
                ", dbFieldName='" + dbFieldName + '\'' +
                '}';
    }
}
