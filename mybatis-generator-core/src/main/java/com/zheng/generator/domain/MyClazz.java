package com.zheng.generator.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 表示一个实体中的基本信息
 * 包括：类名，方法名
 * @Author zhenglian
 * @Date 2018/5/29 21:09
 */
public class MyClazz implements Serializable {
    /**
     * 实体简单类名，不是带包的完整类名，比如GeneratorEntity
     */
    private String className;
    /**
     * 类中定义的属性名列表
     */
    private List<String> fieldNames;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("className", this.className)
                .append("fields", this.fieldNames).build();
    }
}
