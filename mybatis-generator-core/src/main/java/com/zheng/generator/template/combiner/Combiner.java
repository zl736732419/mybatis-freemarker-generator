package com.zheng.generator.template.combiner;

import com.zheng.generator.template.TemplateModelBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * @Author zhenglian
 * @Date 2018/5/29 22:57
 */
public abstract class Combiner {
    private Log log = LogFactory.getLog(Combiner.class);
    
    protected static final String JAVA_PREFIX_PATH = "mybatis-generator-example/src/main/java/";
    protected static final String XML_PREFIX_PATH = "mybatis-generator-example/src/main/resources/";
    
    protected static final String SEPERATOR = ".";
    protected Map<String, Object> model;
    
    @Autowired
    @Qualifier("myFreeMarkerConfiguration")
    private Configuration cfg;
    
    /**
     * 获取当前目标文件所在的目录路径
     * @return
     */
    public String getDirPath() {
        String basePackage = (String) model.get(TemplateModelBuilder.PACKAGE);
        StringBuilder builder = new StringBuilder(basePackage);
        builder.append(SEPERATOR);
        builder.append(getSubPackage());
        return builder.toString();
    }

    /**
     * 获取当前需要使用的模板文件名
     * @return
     */
    protected abstract String getTemplateName();

    /**
     * 因为mapper xml文件与java文件不再同一个classpath，所以这里需要下游明确指定
     * @return
     */
    protected abstract String getPrefixPath();
    
    /**
     * 获取当前目标文件所在的子目录
     * 基于用户设置的domain.package的父包
     * 最后生成的路径为: ${domain.package}/../getSubPackage()
     * @return
     */
    protected abstract String getSubPackage();

    /**
     * 因为这里有两个文件，并且需要对非Base开头的模板进行特殊处理
     * @param templateName
     * @return
     */
    protected String getFileName(String templateName) {
        String fileNameWithoutSuffix = templateName.substring(0, templateName.lastIndexOf("."));
        String suffix = ".java";
        if (Objects.equals(getPrefixPath(), XML_PREFIX_PATH)) {
            suffix = ".xml";
        }
        StringBuilder builder = new StringBuilder();
        if (!templateName.startsWith("Base")) {
            String entityUppercase = (String) model.get(TemplateModelBuilder.ENTITY_UPPERCASE);
            builder.append(entityUppercase);
        }
        builder.append(fileNameWithoutSuffix).append(suffix);
        return builder.toString();
    }
    
    /**
     * 组合模板和数据生成最终文件
     * @param model
     */
    public void combineTemplate(Map<String, Object> model) {
        this.model = model;
        String templateNames = getTemplateName();
        if (StringUtils.isEmpty(templateNames)) {
            log.warn(this.getClass() + "组合器没有指定具体模板，无法生成目标文件");
            return;
        }
        String[] templates = StringUtils.tokenizeToStringArray(templateNames, ",; \t\n");
        Arrays.stream(templates)
                .forEach(templateName -> {
                    try {
                        Template template = cfg.getTemplate(templateName);
                        String parentDirPackage = getDirPath() + SEPERATOR;
                        String parentDirPath = ClassUtils.convertClassNameToResourcePath(parentDirPackage);
                        String fileName = getFileName(templateName);
                        String path = getPrefixPath() + parentDirPath + fileName;
                        File file = new File(path);
                        FileOutputStream output = FileUtils.openOutputStream(file);
                        Writer out = new OutputStreamWriter(output);
                        template.process(model, out);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("生成目标文件失败，原因：" + e.getMessage());
                    }
                });
    }
}
