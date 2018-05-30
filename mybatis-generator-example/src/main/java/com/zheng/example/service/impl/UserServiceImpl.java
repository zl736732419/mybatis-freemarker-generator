package com.zheng.example.service.impl;
import com.zheng.example.dao.BaseDao;
import com.zheng.example.dao.UserDao;
import com.zheng.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
* User业务逻辑实现
* @Author zhenglian
* @Date 23:24 2018-05-30
*/
@Service
public class UserServiceImpl extends BaseServiceImpl<User> {
@Autowired
private UserDao userDao;
@Override
protected BaseDao<User> getBaseDao() {
return userDao;
}
}