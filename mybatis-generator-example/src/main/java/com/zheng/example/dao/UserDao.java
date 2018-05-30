package com.zheng.example.dao;
import com.zheng.example.domain.User;
import org.apache.ibatis.annotations.Mapper;
/**
* @Author zhenglian
* @Date 22:48 2018-05-30
*/
@Mapper
public interface UserDao extends BaseDao<User> {
}