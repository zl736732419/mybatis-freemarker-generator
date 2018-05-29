package com.zheng.generator.template;

import com.zheng.generator.domain.MyClazz;
import com.zheng.generator.formatter.CamelFormatter;
import com.zheng.generator.formatter.Formatter;
import com.zheng.generator.formatter.UnderscoreFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 模板数据组装工具
 * @Author zhenglian
 * @Date 2018/5/29 22:31
 */
@Component
public class TemplateModelBuilder {
    public static final String AUTHOR = "author";
    public static final String CREATE_TIME = "createTime";
    public static final String ENTITY_UPPERCASE = "entityUppercase";
    public static final String ENTITY_LOWERCASE = "entityLowercase";
    public static final String PACKAGE = "package";
    public static final String FIELDS = "fields";
    
    @Value("${author}")
    private String author;
    @Value("${domain.package}")
    private String domainPackage;
    @Value("${db.field.style}")
    private String dbFieldStyle = "camel";
    
    @Autowired
    private CamelFormatter camelFormatter;
    @Autowired
    private UnderscoreFormatter underscoreFormatter;
    
    /**
     * 根据一个类生成一个数据模型，用于模板变量替换
     * @param clazz
     * @return
     */
    public Map<String, Object> buildDataModel(MyClazz clazz) {
        if (!Optional.ofNullable(clazz).isPresent()) {
            throw new RuntimeException("构建数据对象时报错，对应实体类不能为空");
        }
        
        Map<String, Object> map = new HashMap<>();
        map.put(AUTHOR, author);
        map.put(CREATE_TIME, new Date());
        
        String clazzName = clazz.getClassName();
        map.put(ENTITY_UPPERCASE, clazzName);
        map.put(ENTITY_LOWERCASE, camelFormatter.format(clazzName));
        
        int endIndex = domainPackage.lastIndexOf(".");
        String pkg = domainPackage.substring(0, endIndex);
        map.put(PACKAGE, pkg);

        List<String> fieldNames = buildFieldNames(clazz.getFieldNames());
        map.put(FIELDS, fieldNames);
        
        return map;
    }

    /**
     * 根据用户自定义设置
     * @param fieldNames
     * @return
     */
    private List<String> buildFieldNames(List<String> fieldNames) {
        if (CollectionUtils.isEmpty(fieldNames)) {
            return null;
        }

        final Formatter formatter;
        if (Objects.equals(dbFieldStyle, underscoreFormatter.getName())) {
            formatter = underscoreFormatter;
        } else {
            formatter = camelFormatter;
        }
        
        List<String> result = new ArrayList<>();
        fieldNames.forEach(fieldName -> {
            result.add(formatter.format(fieldName));
        });
        return result;
    }


}
