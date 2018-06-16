package com.zheng.springboot.shiro.service.impl;

import com.zheng.springboot.shiro.dao.BaseDao;
import com.zheng.springboot.shiro.dao.UserRoleDao;
import com.zheng.springboot.shiro.domain.UserRole;
import com.zheng.springboot.shiro.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * UserRole业务逻辑实现
 * @Author zhenglian
 * @Date 10:30 2018-06-16
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService {

    @Resource
    private UserRoleDao userRoleDao;

    @Override
    protected BaseDao<UserRole> getBaseDao() {
        return userRoleDao;
    }
}