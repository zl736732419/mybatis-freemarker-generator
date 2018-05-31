package com.zheng.generator.monitor;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * 直接访问http://localhost:8080/druid
 * @Description: 监控数据库性能
 * @author: zhenglian
 * @date: 17/4/21 上午11:23
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/druid/*",
        initParams={
//                @WebInitParam(name="allow",value="192.168.16.110,127.0.0.1"),// IP白名单 (没有配置或者为空，则允许所有访问)
//                @WebInitParam(name="deny",value="192.168.16.111"),// IP黑名单 (存在共同时，deny优先于allow)
                @WebInitParam(name="loginUsername",value="test"),// 用户名
                @WebInitParam(name="loginPassword",value="test"),// 密码
                @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能
        })
public class DruidStatViewServlet extends StatViewServlet {
}
