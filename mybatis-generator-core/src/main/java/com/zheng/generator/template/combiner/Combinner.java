package com.zheng.generator.template.combiner;

import com.zheng.generator.template.TemplateModelBuilder;
import freemarker.template.Template;
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

/**
 * @Author zhenglian
 * @Date 2018/5/29 22:57
 */
public abstract class Combinner {
    private Log log = LogFactory.getLog(Combinner.class);
    
    protected static final String JAVA_PREFIX_PATH = "src/main/java/";
    protected static final String XML_PREFIX_PATH = "src/main/resources/";
    
    protected static final String SEPERATOR = ".";
    protected Map<String, Object> model;
    
    @Autowired
    @Qualifier("myFreeMarkerConfiguration")
    private freemarker.template.Configuration cfg;
    
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

    protected abstract String getFileName(String templateName);
    
    /**
     * 组合模板和数据生成最终文件
     * @param model
     */
    public void combineTemplate(Map<String, Object> model) {
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
                        String fileName = getFileName(templateName);
                        String fullPackageFileName = getDirPath() + SEPERATOR + fileName;
                        String path = ClassUtils.convertClassNameToResourcePath(fullPackageFileName);
                        path = getPrefixPath() + path;
                        File file = new File(path);
                        // 如果已经生成过就不再生成
                        if (file.exists()) { 
                            return;
                        }
                        Writer out = new OutputStreamWriter(new FileOutputStream(file));
                        template.process(model, out);
                    } catch (Exception e) {
                        throw new RuntimeException("生成目标文件失败，原因：" + e.getMessage());
                    }
                });
    }
}
