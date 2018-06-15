package com.zheng.springboot.dao;
import com.zheng.springboot.domain.User;
import org.apache.ibatis.annotations.Mapper;
/**
* @Author zhenglian
* @Date 16:30 2018-06-15
*/
@Mapper
public interface UserDao extends BaseDao<User> {
}