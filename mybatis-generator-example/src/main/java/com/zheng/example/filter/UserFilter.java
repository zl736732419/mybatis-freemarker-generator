package com.zheng.example.filter;

import java.io.Serializable;
import java.util.Date;

/**
 * User实体条件查询过滤器
 * @Author zhenglian
 * @Date 18:37 2018-06-04
 */
public class UserFilter extends BaseFilter {
    private String nickName;
    private Integer age;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;

    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Integer getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}

