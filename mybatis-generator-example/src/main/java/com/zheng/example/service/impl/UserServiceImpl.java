package com.zheng.example.service.impl;

import com.zheng.example.dao.BaseDao;
import com.zheng.example.dao.UserDao;
import com.zheng.example.domain.User;
import com.zheng.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * User业务逻辑实现
 * @Author zhenglian
 * @Date 09:38 2018-06-02
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