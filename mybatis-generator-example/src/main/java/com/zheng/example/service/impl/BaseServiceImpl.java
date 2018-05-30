package com.zheng.example.service.impl;

import com.zheng.example.dao.BaseDao;
import com.zheng.example.service.BaseService;

import java.util.List;
import java.util.Optional;

/**
 * 共用服务实现类
 * Created by zhenglian on 22:53 2018-05-30..
 *
 * @param <T> 具体实例
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    /**
     * 分页一页显示条数
     */
    public static final Integer MAX_SIZE = 20;
    
    @Override
    public int insert(T record){
        if (!Optional.ofNullable(record).isPresent()) {
            return 0;
        }
        return getBaseDao().insert(record);
    }

    @Override
    public int deleteById(Integer id){
        if (!Optional.ofNullable(id).isPresent() || id < 0) {
            return 0;
        }
        return getBaseDao().deleteById(id);
    }

    @Override
    public int update(T record){
        if (!Optional.ofNullable(record).isPresent()) {
            return 0;
        }
        return getBaseDao().update(record);
    }

    @Override
    public T selectById(Integer id){
        if (!Optional.ofNullable(id).isPresent() ||
                id < 0) {
            return null;
        }
        
        return getBaseDao().selectById(id);
    }

    @Override
    public List<T> findAll() {
        return getBaseDao().findAll();
    }

    @Override
    public int countAll() {
        return getBaseDao().countAll();
    }

    /**
     * Gets base dao.
     *
     * @return the base dao
     */
    protected abstract BaseDao<T> getBaseDao();
}