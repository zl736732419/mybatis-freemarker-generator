package com.zheng.springboot.service;

import com.zheng.springboot.filter.BaseFilter;
import com.zheng.generator.domain.mybatis.MyPageBounds;
import com.zheng.generator.domain.mybatis.MyPageList;
import org.apache.ibatis.annotations.Param;

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
    * 根据查询条件分页查询
    * @param filter
    * @param myPageBounds
    * @return
    */
    MyPageList<T> listPageByFilter(@Param("filter") BaseFilter filter, MyPageBounds myPageBounds);

    /**
    * 无条件分页查询
    * @param myPageBounds
    * @return
    */
    MyPageList<T> listPage(MyPageBounds myPageBounds);

    /**
    * 根据查询条件查询列表
    * @param filter
    * @return
    */
    List<T> listByFilter(@Param("filter") BaseFilter filter);
}
