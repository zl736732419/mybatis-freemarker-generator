package com.zheng.generator.template.combiner;

import org.springframework.stereotype.Component;

/**
 * mapper配置文件生成器
 * @Author zhenglian
 * @Date 14:18 2018/5/31
 */
@Component
public class MapperCombiner extends Combiner {
    @Override
    protected String getTemplateName() {
        return "Mapper.ftl";
    }

    @Override
    protected String getPrefixPath() {
        return XML_PREFIX_PATH;
    }

    @Override
    protected String getSubPackage() {
        return "mapper";
    }
}
