package com.zheng.generator.runners;

import com.zheng.generator.builders.TemplateModelBuilder;
import com.zheng.generator.parsers.ClassParser;
import com.zheng.generator.scanner.PackageScanner;
import com.zheng.generator.template.TemplateFacade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 当springboot启动时运行
 * 这里主要是调用实体扫描器，扫描domain.package包下的目标实体
 * @Author zhenglian
 * @Date 2018/5/28 23:37
 */
@Component
@Order(value = 2)
public class StartRunner implements ApplicationRunner {
    private Log logger = LogFactory.getLog(this.getClass());
    @Value("${domain.package}")
    private String basePackage;
    
    @Autowired
    private PackageScanner classScanner;
    @Autowired
    private ClassParser classParser;
    @Autowired
    private TemplateModelBuilder modelBuilder;
    @Autowired
    private TemplateFacade templateFacade;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
//        // 扫描包下的类文件
//        logger.debug("扫描包======================" + basePackage);
//        Set<Class> classes = classScanner.scan(basePackage);
//        if (CollectionUtils.isEmpty(classes)) {
//            return;
//        }
//        logger.debug("扫描完成======================");
//
//        // 通过反射获取类名和属性名
//        List<MyClazz> myClazzes = classParser.parseClasses(classes);
//        if (CollectionUtils.isEmpty(myClazzes)) {
//            return;
//        }
//
//        // 创建模板
//        myClazzes.forEach(cls -> {
//            Map<String, Object> model = modelBuilder.buildDataModel(cls);
//            templateFacade.createTemplate(model);
//        });
        
    }
}
