package com.zheng.example.test.service.impl;

import com.zheng.example.domain.User;
import com.zheng.example.test.dao.BaseDao;
import com.zheng.example.test.dao.UserDao;

/**
 * @Author zhenglian
 * @Date 17:58 2018/5/28
 */
//@Service
public class UserServiceImpl extends BaseServiceImpl<User> {

//    @Autowired
    private UserDao userDao;

    @Override
    protected BaseDao<User> getBaseDao() {
        return userDao;
    }
}
