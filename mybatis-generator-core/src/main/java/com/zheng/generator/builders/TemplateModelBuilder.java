package com.zheng.generator.builders;

import com.zheng.generator.domain.MyAttr;
import com.zheng.generator.domain.MyClazz;
import com.zheng.generator.domain.MyDomain;
import com.zheng.generator.domain.MyField;
import com.zheng.generator.domain.db.DBTable;
import com.zheng.generator.formatter.CamelFormatter;
import com.zheng.generator.formatter.Formatter;
import com.zheng.generator.formatter.UnderscoreFormatter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
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
     * 全限定名
     */
    public static final String DOMAIN_PACKAGE_CLS_NAME = "entityPackageClsName";
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
    public static final String DB_ENTITY_ID = "dbEntityId";
    /**
     * 删除属性逻辑删除时有效
     */
    public static final String DELETE_ATTR = "deleteAttr";
    public static final String DB_DELETE_ATTR = "dbDeleteAttr";
    /**
     * 创建时间
     */
    public static final String CREATE_TIME_ATTR = "createTimeAttr";
    public static final String DB_CREATE_TIME_ATTR = "dbCreateTimeAttr";
    /**
     * 更新时间
     */
    public static final String UPDATE_TIME_ATTR = "updateTimeAttr";
    public static final String DB_UPDATE_TIME_ATTR = "dbUpdateTimeAttr";
    /**
     * 表名
     */
    public static final String TABLE_NAME = "tableName";

    /**
     * 实体
     */
    public static final String DOMAIN_ENTITY = "domainEntity";


    @Value("${author}")
    private String author;
    @Value("${domain.package}")
    private String domainPackage;
    @Value("${db.column.style}")
    private String dbFieldStyle = "camel";
    @Value("${mapper.annotation.style}")
    private String mapperAnnotationStyle = "repository";
    @Value("${db.table.prefix}")
    private String tablePrefix="";

    @Resource
    private CamelFormatter camelFormatter;
    @Resource
    private UnderscoreFormatter underscoreFormatter;

    /**
     * 构建db domain数据模型，用于生成实体类
     * @param dbTable
     * @return
     */
    public Map<String, Object> buildDomainModel(DBTable dbTable) {
        if (!Optional.ofNullable(dbTable).isPresent()) {
            throw new RuntimeException("构建数据对象时报错，数据库表实体不能为空");
        }

        Map<String, Object> map = new HashMap<>();
        map.put(AUTHOR, author);
        map.put(CREATE_TIME, new Date());

        int endIndex = domainPackage.lastIndexOf(".");
        String pkg = domainPackage.substring(0, endIndex);
        map.put(PACKAGE, pkg);

        MyDomain domain = buildMyDomain(dbTable);
        map.put(DOMAIN_ENTITY, domain);
        String domainName = domain.getDomainName();
        map.put(ENTITY_UPPERCASE, domainName);

        String domainPackageClsName = new StringBuilder(domainPackage)
                .append(".")
                .append(domainName)
                .toString();
        map.put(DOMAIN_PACKAGE_CLS_NAME, domainPackageClsName);

        return map;
    }

    /**
     * 通过数据库表实体构造domain数据模型
     * @param dbTable
     * @return
     */
    private MyDomain buildMyDomain(DBTable dbTable) {
        String tableName = dbTable.getTableName();
        String tableNameWithoutPrefix = escapeTablePrefix(tableName);
        String domainName = getFormatter().parse(tableNameWithoutPrefix);

        MyDomain domain = new MyDomain();
        domain.setDomainName(domainName);
        domain.setIncludeUtilField(dbTable.isIncludeUtilField());
        List<MyField> fields = new ArrayList<>();
        dbTable.getColumns().stream()
                .filter(item -> Optional.ofNullable(item).isPresent())
                .forEach(item -> {
                    MyField field = new MyField();
                    String javaType = item.getJavaType();
                    field.setFieldType(javaType);
                    String fieldName = getFormatter().parse(item.getName());
                    field.setFieldName(fieldName);
                    if (Objects.equals(fieldName.toLowerCase(), "date")) {
                        domain.setIncludeUtilField(true);
                    }
                    field.setRemarks(item.getRemarks());
                    fields.add(field);
                });
        domain.setFields(fields);
        return domain;
    }

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
        map.put(DOMAIN_PACKAGE_CLS_NAME, clazz.getPkgClsName());

        int endIndex = domainPackage.lastIndexOf(".");
        String pkg = domainPackage.substring(0, endIndex);
        map.put(PACKAGE, pkg);

        List<MyAttr> attrs = clazz.getAttrs();
        fillAttrNames(attrs);
        map.put(ATTRS, attrs);
        map.put(MAPPER_ANNOTATION_STYLE, mapperAnnotationStyle);

        // id属性
        String entityId = getEntityId(attrs, clazzName);
        map.put(ENTITY_ID, entityId);
        map.put(DB_ENTITY_ID, formatAttrName(entityId));
        // 删除属性
        String deleteAttr = getDeleteAttr(attrs, clazzName);
        map.put(DELETE_ATTR, deleteAttr);
        map.put(DB_DELETE_ATTR, formatAttrName(deleteAttr));
        // 创建时间
        String createTimeAttr = getCreateTimeAttr(attrs, clazzName);
        map.put(CREATE_TIME_ATTR, createTimeAttr);
        map.put(DB_CREATE_TIME_ATTR, formatAttrName(createTimeAttr));
        // 更新时间
        String updateTimeAttr = getUpdateTimeAttr(attrs, clazzName);
        map.put(UPDATE_TIME_ATTR, updateTimeAttr);
        map.put(DB_UPDATE_TIME_ATTR, formatAttrName(updateTimeAttr));

        String tableName = buildTableName(clazzName);
        map.put(TABLE_NAME, tableName);

        return map;
    }

    /**
     * 获取更新时间属性
     * @param attrs
     * @param clazzName
     * @return
     */
    private String getUpdateTimeAttr(List<MyAttr> attrs, String clazzName) {
        for (MyAttr attr : attrs) {
            if (attr.isUpdateTimeAttr()) {
                return attr.getAttrName();
            }
        }
        throw new RuntimeException("实体【"+clazzName+"】缺少Date类型字段updateTime");
    }

    /**
     * 获取创建时间属性
     * @param attrs
     * @param clazzName
     * @return
     */
    private String getCreateTimeAttr(List<MyAttr> attrs, String clazzName) {
        for (MyAttr attr : attrs) {
            if (attr.isCreateTimeAttr()) {
                return attr.getAttrName();
            }
        }
        throw new RuntimeException("实体【"+clazzName+"】缺少Date类型字段createTime");
    }

    /**
     * 获取删除属性
     * @param attrs
     * @param clazzName
     * @return
     */
    private String getDeleteAttr(List<MyAttr> attrs, String clazzName) {
        for (MyAttr attr : attrs) {
            if (attr.isDeleteAttr()) {
                return attr.getAttrName();
            }
        }
        throw new RuntimeException("实体【"+clazzName+"】缺少整型字段isDelete");
    }

    /**
     * 获取实体唯一id属性
     * @param attrs
     * @param clazzName
     * @return
     */
    private String getEntityId(List<MyAttr> attrs, String clazzName) {
        for (MyAttr attr : attrs) {
            if (attr.isIdAttr()) {
                return attr.getAttrName();
            }
        }
        throw new RuntimeException("当前实体类【"+clazzName+"】没有找到唯一属性id标识，请修正你的实体;id属性名可以是id或者实体类名+Id的形式比如userId");
    }

    /**
     * 从数据库表名反向获取实体名
     * @param tableName
     * @return
     */
    private String escapeTablePrefix(String tableName) {
        if (StringUtils.isEmpty(tablePrefix)) {
            return tableName;
        }
        if (!tableName.startsWith(tablePrefix)) {
            return tableName;
        }
        tableName = tableName.substring(tablePrefix.length());
        return tableName;
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
    private void fillAttrNames(List<MyAttr> attrs) {
        if (CollectionUtils.isEmpty(attrs)) {
            return;
        }

        attrs.forEach(attr -> {
            String fieldName = formatAttrName(attr.getAttrName());
            attr.setDbFieldName(fieldName);
        });
    }

    /**
     * 获取不同风格的字符串展现形式
     * @param attrName
     * @return
     */
    private String formatAttrName(String attrName) {
        if (StringUtils.isEmpty(attrName)) {
            return null;
        }
        Formatter formatter = getFormatter();
        String result = formatter.format(attrName);
        return result;
    }

    /**
     * 获取字符格式化器
     * @return
     */
    private Formatter getFormatter() {
        final Formatter formatter;
        if (Objects.equals(dbFieldStyle, underscoreFormatter.getName())) {
            formatter = underscoreFormatter;
        } else {
            formatter = camelFormatter;
        }
        return formatter;
    }


}
