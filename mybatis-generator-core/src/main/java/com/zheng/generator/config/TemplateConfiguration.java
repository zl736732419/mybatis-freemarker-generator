package com.zheng.generator.config;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

/**
 * 统一配置template需要的类
 * @Author zhenglian
 * @Date 2018/5/28 22:01
 */
@Configuration
public class TemplateConfiguration {
    @Bean("myFreeMarkerConfiguration")
    public freemarker.template.Configuration configuration() {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(
                freemarker.template.Configuration.VERSION_2_3_28);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());

        // 加载filter
        ClassTemplateLoader filterImplLoader = new ClassTemplateLoader(getClass(), "/templates/filter");
        // 加载dao接口
        ClassTemplateLoader daoImplLoader = new ClassTemplateLoader(getClass(), "/templates/dao");
        // 加载service接口
        ClassTemplateLoader serviceLoader = new ClassTemplateLoader(getClass(), "/templates/service");
        // 加载service实现
        ClassTemplateLoader serviceImplLoader = new ClassTemplateLoader(getClass(), "/templates/service/impl");

        TemplateLoader[] loaders = new TemplateLoader[] {filterImplLoader, daoImplLoader, serviceLoader, serviceImplLoader};
        MultiTemplateLoader multiLoader = new MultiTemplateLoader(loaders);
        cfg.setTemplateLoader(multiLoader);
        return cfg;
    }
}
