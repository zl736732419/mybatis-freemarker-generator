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
     * 包全限定名
     */
    private String pkgClsName;

    /**
     * 属性
     */
    private List<MyAttr> attrs;


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPkgClsName() {
        return pkgClsName;
    }

    public void setPkgClsName(String pkgClsName) {
        this.pkgClsName = pkgClsName;
    }

    public List<MyAttr> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<MyAttr> attrs) {
        this.attrs = attrs;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("className", this.className)
                .append("pkgClsName", this.pkgClsName)
                .append("attrs", this.attrs)
                .build();
    }
}
