package com.zheng.example.dao;
import com.zheng.example.domain.User;
import org.apache.ibatis.annotations.Mapper;
/**
* @Author zhenglian
* @Date 09:38 2018-06-02
*/
@Mapper
public interface UserDao extends BaseDao<User> {
}