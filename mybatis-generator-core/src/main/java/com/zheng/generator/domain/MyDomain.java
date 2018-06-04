package com.zheng.generator.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhenglian
 * @Date 14:36 2018/6/4
 */
public class MyDomain {
    private String domainName;
    private List<MyField> fields = new ArrayList<>();

    /**
     * 是否存在java.util.*包中的属性字段
     */
    private boolean includeUtilField = false;

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public List<MyField> getFields() {
        return fields;
    }

    public void setFields(List<MyField> fields) {
        this.fields = fields;
    }

    public boolean isIncludeUtilField() {
        return includeUtilField;
    }

    public void setIncludeUtilField(boolean includeUtilField) {
        this.includeUtilField = includeUtilField;
    }
}
