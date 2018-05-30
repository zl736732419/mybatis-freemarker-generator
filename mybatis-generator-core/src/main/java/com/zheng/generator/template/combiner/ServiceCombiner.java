package com.zheng.generator.template.combiner;

import org.springframework.stereotype.Component;

/**
 * Service业务层代码生成器
 * @Author zhenglian
 * @Date 2018/5/29 22:56
 */
@Component
public class ServiceCombiner extends Combinner {
    @Override
    protected String getTemplateName() {
        return "BaseService.ftl, Service.ftl";
    }

    @Override
    protected String getSubPackage() {
        return "service";
    }

    @Override
    protected String getPrefixPath() {
        return JAVA_PREFIX_PATH;
    }
}
