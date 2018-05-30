package com.zheng.example.test.dao;

import com.zheng.example.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author zhenglian
 * @Date 18:05 2018/5/28
 */
@Mapper
public interface UserDao extends BaseDao<User> {
}
