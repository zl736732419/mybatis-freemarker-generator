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

    /**
     * 是否是id属性
     */
    private boolean isIdAttr = false;

    /**
     * 是否是创建时间属性
     */
    private boolean isCreateTimeAttr = false;
    /**
     * 是否是更新时间属性
     */
    private boolean isUpdateTimeAttr = false;
    /**
     * 是否是删除属性
     */
    private boolean isDeleteAttr = false;


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

    public boolean isIdAttr() {
        return isIdAttr;
    }

    public void setIdAttr(boolean idAttr) {
        isIdAttr = idAttr;
    }

    public boolean isCreateTimeAttr() {
        return isCreateTimeAttr;
    }

    public void setCreateTimeAttr(boolean createTimeAttr) {
        isCreateTimeAttr = createTimeAttr;
    }

    public boolean isUpdateTimeAttr() {
        return isUpdateTimeAttr;
    }

    public void setUpdateTimeAttr(boolean updateTimeAttr) {
        isUpdateTimeAttr = updateTimeAttr;
    }

    public boolean isDeleteAttr() {
        return isDeleteAttr;
    }

    public void setDeleteAttr(boolean deleteAttr) {
        isDeleteAttr = deleteAttr;
    }

    @Override
    public String toString() {
        return "MyAttr{" +
                "isIdAttr='" + isIdAttr + '\'' +
                "attrName='" + attrName + '\'' +
                ", attrType='" + attrType + '\'' +
                ", dbFieldName='" + dbFieldName + '\'' +
                ", isCreateTimeAttr='" + isCreateTimeAttr + '\'' +
                ", isUpdateTimeAttr='" + isUpdateTimeAttr + '\'' +
                ", isDeleteAttr='" + isDeleteAttr + '\'' +
                '}';
    }
}
