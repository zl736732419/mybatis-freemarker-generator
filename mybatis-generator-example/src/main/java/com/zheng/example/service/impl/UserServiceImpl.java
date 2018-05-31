package com.zheng.example.service.impl;

import com.zheng.example.dao.BaseDao;
import com.zheng.example.dao.UserDao;
import com.zheng.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User业务逻辑实现
 * @Author zhenglian
 * @Date 15:10 2018-05-31
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> {

    @Autowired
    private UserDao userDao;

    @Override
    protected void fillData(User user) {
    }

    @Override
    protected BaseDao<User> getBaseDao() {
        return userDao;
    }
}