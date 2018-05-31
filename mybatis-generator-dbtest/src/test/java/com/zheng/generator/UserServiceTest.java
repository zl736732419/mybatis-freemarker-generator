package com.zheng.generator;

import com.zheng.generator.domain.User;
import com.zheng.generator.domain.mybatis.MyPageBounds;
import com.zheng.generator.domain.mybatis.MyPageList;
import com.zheng.generator.filter.UserFilter;
import com.zheng.generator.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author zhenglian
 * @Date 2018/5/20 10:32
 */
public class UserServiceTest extends BaseServiceTest {
    @Resource
    private UserService userService;

    @Test
    public void save() {
        User user = new User();
        user.setNickName("xiaozheng");
        user.setAge(25);
        user.setIsDelete(0);
        userService.insert(user);
    }

    @Test
    public void update() {
        User user = userService.selectById(1);
        user.setNickName("nidaye");
        user.setAge(20);
        userService.update(user);
    }
    
    @Test
    public void listAll() {
        List<User> users = userService.findAll();
        System.out.println(users);
    }

    @Test
    public void listByPage() {
        MyPageList<User> myPageList = userService.listPage(new MyPageBounds(1,1));
        System.out.println(myPageList.getItems());
    }

    @Test
    public void listByFilter() {
        UserFilter userFilter = new UserFilter();
        userFilter.setNickName("xiaozheng");
        List<User> users = userService.listByFilter(userFilter);
        System.out.println(users);
    }

    @Test
    public void listByPageFilter() {
        UserFilter userFilter = new UserFilter();
        userFilter.setNickName("xiaozheng");
        MyPageList<User> users = userService.listPageByFilter(userFilter, new MyPageBounds(1,1));
        System.out.println(users);
    }

    @Test
    public void selectById() {
        User user = userService.selectById(1);
        System.out.println(user);
    }

    @Test
    public void delete() {
        userService.deleteById(1);
    }


}
