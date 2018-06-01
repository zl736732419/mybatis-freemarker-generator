package com.zheng.generator.parsers;

import com.zheng.generator.domain.MyAttr;
import com.zheng.generator.domain.MyClazz;
import com.zheng.generator.formatter.CamelFormatter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 扫描给定的类，并获取对应的类名和属性名列表
 * @Author zhenglian
 * @Date 2018/5/29 21:13
 */
@Component
public class ClassParser {
    private Log log = LogFactory.getLog(ClassParser.class);

    @Value("${domain.attr.createTime}")
    private String createTimeAttr;
    @Value("${domain.attr.updateTime}")
    private String updateTimeAttr;
    @Value("${domain.attr.isDelete}")
    private String isDeleteAttr;

    @Autowired
    private CamelFormatter camelFormatter;


    /**
     * 解析多个类字节码解析成MyClazz对象
     * @param clazzes
     * @return
     */
    public List<MyClazz> parseClasses(Collection<Class> clazzes) {
        if (CollectionUtils.isEmpty(clazzes)) {
            return null;
        }
        List<MyClazz> myClazzes = new ArrayList<>();
        clazzes.stream()
                .filter(clazz -> Optional.ofNullable(clazz).isPresent())
                .forEach(clazz -> {
                    MyClazz myClazz = parseClass(clazz);
                    if (!Optional.ofNullable(myClazz).isPresent()) {
                        return;
                    }
                    myClazzes.add(myClazz);
                });
        return myClazzes;
    }
    
    /**
     * 将给定的类字节码解析成MyClazz对象
     * @param clazz
     * @return
     */
    public MyClazz parseClass(Class<?> clazz) {
        if (!Optional.ofNullable(clazz).isPresent()
                || Objects.equals(clazz, Object.class)) {
            return null;
        }
        
        MyClazz myClazz = new MyClazz();
        String clazzName = clazz.getSimpleName();
        myClazz.setClassName(clazzName);

        String pkgClazzName = clazz.getName();
        myClazz.setPkgClsName(pkgClazzName);

        List<MyAttr> attrs = fillFieldInfos(clazz);
        if (CollectionUtils.isEmpty(attrs)) {
            log.debug("给定的实体类：" + clazzName + "没有属性，该类已被忽略");
            return null;
        }
        myClazz.setAttrs(attrs);
        return myClazz;
    }

    /**
     * 获取类的属性列表
     * @param clazz
     * @return
     */
    private List<MyAttr> fillFieldInfos(Class<?> clazz) {
        if (!Optional.ofNullable(clazz).isPresent()
                || Objects.equals(clazz, Object.class)) {
            return null;
        }

        Field[] fields = clazz.getDeclaredFields();
        if (ArrayUtils.isEmpty(fields)) {
            return null;
        }

        List<MyAttr> attrs = new ArrayList<>();
        Arrays.stream(fields)
                .filter(field -> Optional.ofNullable(field).isPresent())
                .forEach(field -> {
                    Class<?> type = field.getType();
                    // 排除掉复合类型的属性，这里只考虑对简单类型的属性
                    if (isComplexType(type)) {
                        return;
                    }
                    String name = field.getName();
                    MyAttr attr = new MyAttr();

                    attr.setAttrName(name);
                    String typeName = type.getSimpleName();
                    attr.setAttrType(typeName);

                    boolean id = isId(name, clazz.getSimpleName());
                    attr.setIdAttr(id);




                    attrs.add(attr);
                });

        return attrs;
    }

    /**
     * 判断当前属性是否是id属性
     * id属性可以允许两种形式：id/${entityClassName}Id
     * 比如id, userId
     * @param attrName
     * @param className
     * @return
     */
    private boolean isId(String attrName, String className) {
        if (Objects.equals(attrName, "id")) {
            return true;
        }

        String entityId = camelFormatter.format(className) + "Id";
        if (Objects.equals(attrName, entityId)) {
            return true;
        }
        return false;
    }

    /**
     * 比较两个属性名称是否相同
     * @param sourceAttrName 实体中的属性名
     * @param targetAttrName 要比较的目标值
     * @return
     */
    private boolean matchAttrName(String sourceAttrName, String targetAttrName) {
        return Objects.equals(sourceAttrName, targetAttrName);
    }

    /**
     * 判断类型是否是复合类型
     * @param type
     * @return
     */
    private boolean isComplexType(Class<?> type) {
        if (!Optional.ofNullable(type).isPresent()) {
            return true;
        }
        String typeClass = type.getName();
        
        if (typeClass.contains("List")
                || typeClass.contains("Set")
                || typeClass.contains("Collection")
                || typeClass.contains("[")
                ) {
            return true;
        }
        return false;
    }
}
