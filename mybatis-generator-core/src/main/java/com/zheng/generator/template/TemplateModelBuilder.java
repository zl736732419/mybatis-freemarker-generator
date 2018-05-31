package com.zheng.generator.template;

import com.zheng.generator.domain.MyAttr;
import com.zheng.generator.domain.MyClazz;
import com.zheng.generator.formatter.CamelFormatter;
import com.zheng.generator.formatter.Formatter;
import com.zheng.generator.formatter.UnderscoreFormatter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 模板数据组装工具
 * @Author zhenglian
 * @Date 2018/5/29 22:31
 */
@Component
public class TemplateModelBuilder {
    public static final String AUTHOR = "author";
    public static final String CREATE_TIME = "createTime";
    /**
     * 驼峰命名，首字母大写
     */
    public static final String ENTITY_UPPERCASE = "entityUppercase";
    /**
     * 驼峰命名，首字母小写
     */
    public static final String ENTITY_LOWERCASE = "entityLowercase";
    /**
     * 权限定名
     */
    public static final String ENTITY_PACKAGE_CLS_NAME = "entityPackageClsName";
    /**
     * 父包
     */
    public static final String PACKAGE = "package";
    /**
     * 属性名
     */
    public static final String ATTRS = "attrs";

    /**
     * mapper dao接口采用的注解
     */
    public static final String MAPPER_ANNOTATION_STYLE = "mapperAnnotationStyle";
    /**
     * 实体id
     */
    public static final String ENTITY_ID = "entityId";

    /**
     * 表名
     */
    public static final String TABLE_NAME = "tableName";


    
    @Value("${author}")
    private String author;
    @Value("${domain.package}")
    private String domainPackage;
    @Value("${db.field.style}")
    private String dbFieldStyle = "camel";
    @Value("${mapper.annotation.style}")
    private String mapperAnnotationStyle = "repository";
    @Value("${db.table.prefix}")
    private String tablePrefix="";
    
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
        map.put(ENTITY_PACKAGE_CLS_NAME, clazz.getPkgClsName());
        
        int endIndex = domainPackage.lastIndexOf(".");
        String pkg = domainPackage.substring(0, endIndex);
        map.put(PACKAGE, pkg);

        List<MyAttr> attrs = clazz.getAttrs();
        fillFieldNames(attrs);
        map.put(ATTRS, attrs);
        map.put(MAPPER_ANNOTATION_STYLE, mapperAnnotationStyle);
        map.put(ENTITY_ID, getEntityId(attrs));

        String tableName = buildTableName(clazzName);
        map.put(TABLE_NAME, tableName);

        return map;
    }

    /**
     * 获取实体唯一id属性
     * @param attrs
     * @return
     */
    private Object getEntityId(List<MyAttr> attrs) {
        for (MyAttr attr : attrs) {
            if (attr.isId()) {
                return attr.getAttrName();
            }
        }
        throw new RuntimeException("当前实体类【】没有找到唯一属性id标识，请修正你的实体;id属性名可以是id或者实体类名+Id的形式比如userId");
    }

    /**
     * 生成数据库表名
     * @param clazzName
     * @return
     */
    private String buildTableName(String clazzName) {
        StringBuilder builder = new StringBuilder();
        builder.append(tablePrefix);
        if (StringUtils.isNotEmpty(tablePrefix)) {
            builder.append(UnderscoreFormatter.SEPERATOR);
        }
        builder.append(underscoreFormatter.format(clazzName));
        return builder.toString();
    }

    /**
     * 填充数据库字段名
     * @param attrs
     * @return
     */
    private void fillFieldNames(List<MyAttr> attrs) {
        if (CollectionUtils.isEmpty(attrs)) {
            return;
        }

        final Formatter formatter;
        if (Objects.equals(dbFieldStyle, underscoreFormatter.getName())) {
            formatter = underscoreFormatter;
        } else {
            formatter = camelFormatter;
        }
        
        attrs.forEach(attr -> {
            String fieldName = formatter.format(attr.getAttrName());
            attr.setDbFieldName(fieldName);
        });
    }


}
