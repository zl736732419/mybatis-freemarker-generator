package com.zheng.generator.template.combiner;

import com.zheng.generator.template.TemplateModelBuilder;
import org.springframework.stereotype.Component;

/**
 * Service业务层代码生成器
 * @Author zhenglian
 * @Date 2018/5/29 22:56
 */
@Component
public class ServiceTemplateCombiner extends Combinner {
    @Override
    protected String getTemplateName() {
        return "BaseService.ftl, Service.ftl";
    }

    @Override
    protected String getSubPackage() {
        return "service";
    }

    /**
     * 因为这里有两个文件，并且需要对非Base开头的模板进行特殊处理
     * @param templateName
     * @return
     */
    @Override
    protected String getFileName(String templateName) {
        String fileNameWithoutSuffix = templateName.substring(0, templateName.lastIndexOf("."));
        String suffix = ".java";
        StringBuilder builder = new StringBuilder();
        
        if (!templateName.startsWith("Base")) {
            String entityUppercase = (String) model.get(TemplateModelBuilder.ENTITY_UPPERCASE);
            builder.append(entityUppercase);
        }
        builder.append(fileNameWithoutSuffix).append(suffix);
        return builder.toString();
    }

    @Override
    protected String getPrefixPath() {
        return JAVA_PREFIX_PATH;
    }
}
