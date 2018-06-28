package com.zheng.generator.template.combiner;

import org.springframework.stereotype.Component;

/**
 * 实体代码生成
 * @Author zhenglian
 * @Date 15:03 2018/6/4
 */
@Component
public class DomainCombiner extends Combiner {
    @Override
    protected String getTemplateName() {
        return "domain.ftl";
    }

    @Override
    protected String getPrefixPath() {
        return JAVA_PREFIX_PATH;
    }

    @Override
    protected String getSubPackage() {
        return null;
    }
}
