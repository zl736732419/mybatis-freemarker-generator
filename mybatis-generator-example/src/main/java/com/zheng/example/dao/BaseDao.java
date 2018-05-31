package com.zheng.example.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.zheng.example.filter.BaseFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 通用数据层接口
 * @param <T> 对象类型
 * Created by zhenglian on 10:39 2018-05-31.
 */
public interface BaseDao<T> {
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
    int deleteById(@Param("id") Integer id);

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
    T selectById(@Param("id") Integer id);

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

    /**
    * 根据查询条件分页查询
    * @param filter
    * @param pageBounds
    * @return
    */
    PageList<T> findByFilter(@Param("filter") BaseFilter filter, PageBounds pageBounds);
}