package com.zheng.generator.config;

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
    @Bean
    public freemarker.template.Configuration configuration() {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(
                freemarker.template.Configuration.VERSION_2_3_28);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
        cfg.setClassForTemplateLoading(getClass(), "");
        return cfg;
    }
}
