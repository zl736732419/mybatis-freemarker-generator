package com.zheng.springboot.shiro.service.impl;

import com.zheng.springboot.shiro.dao.BaseDao;
import com.zheng.springboot.shiro.dao.RoleUrlResourceDao;
import com.zheng.springboot.shiro.domain.RoleUrlResource;
import com.zheng.springboot.shiro.service.RoleUrlResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * RoleUrlResource业务逻辑实现
 * @Author zhenglian
 * @Date 10:30 2018-06-16
 */
@Service
public class RoleUrlResourceServiceImpl extends BaseServiceImpl<RoleUrlResource> implements RoleUrlResourceService {

    @Resource
    private RoleUrlResourceDao roleUrlResourceDao;

    @Override
    protected BaseDao<RoleUrlResource> getBaseDao() {
        return roleUrlResourceDao;
    }
}