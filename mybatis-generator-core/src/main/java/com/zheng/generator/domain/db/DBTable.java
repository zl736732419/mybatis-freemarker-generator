package com.zheng.generator.domain.db;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库表信息
 * @Author zhenglian
 * @Date 2018/6/3 23:04
 */
public class DBTable {
    private String tableName;
    private List<DBColumn> columns = new ArrayList<>();
    /**
     * 是否存在java.util.*包中的属性字段
     */
    private boolean includeUtilField = false;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<DBColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DBColumn> columns) {
        this.columns = columns;
    }

    public boolean isIncludeUtilField() {
        return includeUtilField;
    }

    public void setIncludeUtilField(boolean includeUtilField) {
        this.includeUtilField = includeUtilField;
    }

    @Override
    public String toString() {
        return "DBTable{" +
                "tableName='" + tableName + '\'' +
                ",isIncludeUtilField='" + isIncludeUtilField() + '\'' +
                ", columns=" + columns +
                '}';
    }
}
