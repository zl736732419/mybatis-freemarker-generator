package com.zheng.generator.domain;

/**
 * domain中的属性字段
 * @Author zhenglian
 * @Date 14:38 2018/6/4
 */
public class MyField {
    /**
     * 属性类型
     */
    private String fieldType;
    /**
     * 属性名
     */
    private String fieldName;
    /**
     * 属性描述
     */
    private String remarks;

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
