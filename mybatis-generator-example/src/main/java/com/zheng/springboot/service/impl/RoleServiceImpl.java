package com.zheng.springboot.service.impl;

import com.zheng.springboot.dao.BaseDao;
import com.zheng.springboot.dao.RoleDao;
import com.zheng.springboot.domain.Role;
import com.zheng.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * Role业务逻辑实现
 * @Author zhenglian
 * @Date 16:30 2018-06-15
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    protected BaseDao<Role> getBaseDao() {
        return roleDao;
    }
}