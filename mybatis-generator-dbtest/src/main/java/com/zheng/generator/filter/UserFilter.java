package com.zheng.generator.filter;

/**
 * User实体条件查询过滤器
 * @Author zhenglian
 * @Date 16:37 2018-05-31
 */
public class UserFilter extends BaseFilter {
    private String nickName;
    private Integer age;
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
    public Integer getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}

