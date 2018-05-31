package com.zheng.generator.template;

import com.zheng.generator.template.combiner.DaoCombiner;
import com.zheng.generator.template.combiner.FilterCombiner;
import com.zheng.generator.template.combiner.ServiceImplCombiner;
import com.zheng.generator.template.combiner.ServiceCombiner;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 模板生成器，采用外观模式
 * 客户只需要调用这里提供的统一方法即可，不需要知道具体的模板生成细节
 * @Author zhenglian
 * @Date 2018/5/29 22:52
 */
@Component
public class TemplateFacade {
    private Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private DaoCombiner daoCombiner;
    @Autowired
    private ServiceCombiner serviceCombiner;
    @Autowired
    private ServiceImplCombiner serviceImplCombiner;
    @Autowired
    private FilterCombiner filterCombiner;
    
    public void createTemplate(Map<String, Object> model) {
        if (MapUtils.isEmpty(model)) {
            return;
        }
        logger.info("正在生成filter代码文件======================");
        // 生成dao接口源码
        filterCombiner.combineTemplate(model);

        logger.info("正在生成dao接口代码文件======================");
        // 生成dao接口源码
        daoCombiner.combineTemplate(model);
        
        // 生成service接口源码
        logger.info("正在生成service接口代码文件======================");
        serviceCombiner.combineTemplate(model);
        
        // 生成service实现源码
        logger.info("正在生成service业务实现代码文件======================");
        serviceImplCombiner.combineTemplate(model);
        
        logger.info("实体"+model.get(TemplateModelBuilder.ENTITY_UPPERCASE)+"代码文件生成完毕======================");
        
    }
    
}
