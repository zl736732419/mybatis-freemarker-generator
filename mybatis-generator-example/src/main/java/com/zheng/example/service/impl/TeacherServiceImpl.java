package com.zheng.example.service.impl;
import com.zheng.example.dao.BaseDao;
import com.zheng.example.dao.TeacherDao;
import com.zheng.example.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
* Teacher业务逻辑实现
* @Author zhenglian
* @Date 10:39 2018-05-31
*/
@Service
public class TeacherServiceImpl extends BaseServiceImpl<Teacher> {
@Autowired
private TeacherDao teacherDao;
@Override
protected BaseDao<Teacher> getBaseDao() {
return teacherDao;
}
}