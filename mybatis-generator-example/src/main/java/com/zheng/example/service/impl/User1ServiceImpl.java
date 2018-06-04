package com.zheng.example.service.impl;

import com.zheng.example.dao.BaseDao;
import com.zheng.example.dao.User1Dao;
import com.zheng.example.domain.User1;
import com.zheng.example.service.User1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * User1业务逻辑实现
 * @Author zhenglian
 * @Date 17:37 2018-06-04
 */
@Service
public class User1ServiceImpl extends BaseServiceImpl<User1> implements User1Service {

    @Resource
    private User1Dao user1Dao;

    @Override
    protected void fillData(User1 user1) {
    }

    @Override
    protected BaseDao<User1> getBaseDao() {
        return user1Dao;
    }
}