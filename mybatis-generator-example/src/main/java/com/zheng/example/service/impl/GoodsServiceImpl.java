package com.zheng.example.service.impl;

import com.zheng.example.dao.BaseDao;
import com.zheng.example.dao.GoodsDao;
import com.zheng.example.domain.Goods;
import com.zheng.example.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * Goods业务逻辑实现
 * @Author zhenglian
 * @Date 17:37 2018-06-04
 */
@Service
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    @Override
    protected void fillData(Goods goods) {
    }

    @Override
    protected BaseDao<Goods> getBaseDao() {
        return goodsDao;
    }
}