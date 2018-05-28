package com.zheng.generator;

import com.zheng.generator.domain.User;
import org.springframework.stereotype.Service;

/**
 * @Author zhenglian
 * @Date 17:58 2018/5/28
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> {

//    @Autowired
    private UserDao userDao;

    @Override
    protected BaseDao<User> getBaseDao() {
        return userDao;
    }
}