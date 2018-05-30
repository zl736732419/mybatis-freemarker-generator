package com.zheng.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**
 * 代码生成器启动类
 * @Author zhenglian
 * @Date 18:18 2018/5/28
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.zheng.example", "com.zheng.generator"})
public class Application {
    public static void main(String[] args) {
        runWithBuilder(args);
    }

    public static void runWithBuilder(String[] args) {
        new SpringApplicationBuilder()
                .sources(Application.class)
                .run(args);
    }
}
