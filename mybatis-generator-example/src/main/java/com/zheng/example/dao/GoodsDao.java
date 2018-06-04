package com.zheng.example.dao;
import com.zheng.example.domain.Goods;
import org.apache.ibatis.annotations.Mapper;
/**
* @Author zhenglian
* @Date 17:37 2018-06-04
*/
@Mapper
public interface GoodsDao extends BaseDao<Goods> {
}