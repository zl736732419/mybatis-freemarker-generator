package com.zheng.generator.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 当springboot启动时运行
 * 这里主要是调用实体扫描器，扫描domain.package包下的目标实体
 * @Author zhenglian
 * @Date 2018/5/28 23:37
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {
    
    @Value("${domain.package}")
    private String basePackage;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        
        
        
        
    }
}
