package com.zheng.generator.template.combiner;

import org.springframework.stereotype.Component;

/**
 * 用户查询过滤器生成
 * @Author zhenglian
 * @Date 10:18 2018/5/31
 */
@Component
public class FilterCombiner extends Combiner {
    @Override
    protected String getTemplateName() {
        return "BaseFilter.ftl, Filter.ftl";
    }

    @Override
    protected String getPrefixPath() {
        return JAVA_PREFIX_PATH;
    }

    @Override
    protected String getSubPackage() {
        return "filter";
    }
}
