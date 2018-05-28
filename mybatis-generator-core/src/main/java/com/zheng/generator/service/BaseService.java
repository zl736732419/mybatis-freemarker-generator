package com.zheng.generator.service;

import java.util.List;

/**
 * 基本业务接口
 * @Author zhenglian
 * @Date 17:52 2018/5/28
 */
public interface BaseService<T> {
    /**
     * 插入对象
     * @param record 对象
     * @return 插入条数
     */
    int insert(T record);

    /**
     * 删除对象
     * @param id 删除对象id
     * @return 被删除记录条数
     */
    int deleteById(Integer id);

    /**
     * 更新对象
     * @param record 对象
     * @return 更新的条数
     */
    int update(T record);

    /**
     * 查询对象
     * @param id 对象id
     * @return 对象
     */
    T selectById(Integer id);

    /**
     * 查询所有记录
     * @return
     */
    List<T> findAll();

    /**
     * 统计所有记录条数
     * @return
     */
    int countAll();


}
