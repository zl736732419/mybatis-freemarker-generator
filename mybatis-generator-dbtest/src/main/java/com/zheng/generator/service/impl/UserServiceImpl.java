package com.zheng.generator.service.impl;


import com.zheng.generator.dao.BaseDao;
import com.zheng.generator.dao.UserDao;
import com.zheng.generator.domain.User;
import com.zheng.generator.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User业务逻辑实现
 * @Author zhenglian
 * @Date 16:37 2018-05-31
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    protected void fillData(User user) {
    }

    @Override
    protected BaseDao<User> getBaseDao() {
        return userDao;
    }
}