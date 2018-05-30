package com.zheng.generator.template.combiner;

import org.springframework.stereotype.Component;

/**
 * Dao接口生成
 * @Author zhenglian
 * @Date 2018/5/30 22:42
 */
@Component
public class DaoCombiner extends Combinner {
    @Override
    protected String getTemplateName() {
        return "Dao.ftl, BaseDao.ftl";
    }

    @Override
    protected String getPrefixPath() {
        return JAVA_PREFIX_PATH;
    }

    @Override
    protected String getSubPackage() {
        return "dao";
    }
}
