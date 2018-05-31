package com.zheng.example.dao;
import com.zheng.example.domain.User;
import org.apache.ibatis.annotations.Mapper;
/**
* @Author zhenglian
* @Date 15:10 2018-05-31
*/
@Mapper
public interface UserDao extends BaseDao<User> {
}