package com.zheng.generator.dao;

import com.zheng.generator.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @Author zhenglian
* @Date 16:37 2018-05-31
*/
@Mapper
public interface UserDao extends BaseDao<User> {
}