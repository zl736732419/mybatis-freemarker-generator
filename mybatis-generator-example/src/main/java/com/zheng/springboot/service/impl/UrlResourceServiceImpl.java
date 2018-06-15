package com.zheng.springboot.service.impl;

import com.zheng.springboot.dao.BaseDao;
import com.zheng.springboot.dao.UrlResourceDao;
import com.zheng.springboot.domain.UrlResource;
import com.zheng.springboot.service.UrlResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * UrlResource业务逻辑实现
 * @Author zhenglian
 * @Date 16:30 2018-06-15
 */
@Service
public class UrlResourceServiceImpl extends BaseServiceImpl<UrlResource> implements UrlResourceService {

    @Resource
    private UrlResourceDao urlResourceDao;

    @Override
    protected BaseDao<UrlResource> getBaseDao() {
        return urlResourceDao;
    }
}