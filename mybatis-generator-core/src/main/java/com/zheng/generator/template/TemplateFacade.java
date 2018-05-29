package com.zheng.generator.template;

import com.zheng.generator.template.combiner.ServiceTemplateCombiner;
import org.apache.commons.collections4.MapUtils;
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
    
    @Autowired
    private ServiceTemplateCombiner serviceTemplateCombiner;
    
    public void createTemplate(Map<String, Object> model) {
        if (MapUtils.isEmpty(model)) {
            return;
        }
        // 生成service源码
        serviceTemplateCombiner.combineTemplate(model);
    }
    
}
