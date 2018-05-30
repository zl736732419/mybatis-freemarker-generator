package com.zheng.generator.template.combiner;

import org.springframework.stereotype.Component;

/**
 * @Author zhenglian
 * @Date 2018/5/30 22:31
 */
@Component
public class ServiceImplCombiner extends Combinner {
    @Override
    protected String getTemplateName() {
        return "BaseServiceImpl.ftl, ServiceImpl.ftl";
    }

    @Override
    protected String getPrefixPath() {
        return JAVA_PREFIX_PATH;
    }

    @Override
    protected String getSubPackage() {
        return "service.impl";
    }
}
