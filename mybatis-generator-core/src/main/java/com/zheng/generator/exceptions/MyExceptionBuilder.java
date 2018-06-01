package com.zheng.generator.exceptions;

/**
 * 异常构造器，这里构造RuntimeException异常
 * @Author zhenglian
 * @Date 22:24 2018/6/1
 */
public class MyExceptionBuilder {

    private MyExceptionBuilder() {};

    public static RuntimeException build(String message) {
        RuntimeException runtimeException = new RuntimeException(message);
        return runtimeException;
    }

}
