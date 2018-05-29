package com.zheng.generator.parsers;

import com.zheng.generator.domain.MyClazz;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 扫描给定的类，并获取对应的类名和属性名列表
 * @Author zhenglian
 * @Date 2018/5/29 21:13
 */
@Component
public class ClassParser {
    private Log log = LogFactory.getLog(ClassParser.class);

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
        
        String clazzName = clazz.getSimpleName();
        MyClazz myClazz = new MyClazz();
        myClazz.setClassName(clazzName);
        
        List<String> fieldNames = getFieldNames(clazz);
        if (CollectionUtils.isEmpty(fieldNames)) {
            log.debug("给定的实体类：" + clazzName + "没有属性，该类已被忽略");
            return null;
        }
        myClazz.setFieldNames(fieldNames);
        return myClazz;
    }

    /**
     * 获取类的属性列表
     * @param clazz
     * @return
     */
    private List<String> getFieldNames(Class<?> clazz) {
        if (!Optional.ofNullable(clazz).isPresent()
                || Objects.equals(clazz, Object.class)) {
            return null;
        }

        List<String> fieldNames = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        if (ArrayUtils.isEmpty(fields)) {
            return null; 
        }
        Arrays.stream(fields)
                .filter(field -> Optional.ofNullable(field).isPresent())
                .forEach(field -> {
                    Class<?> type = field.getType();
                    // 排除掉复合类型的属性，这里只考虑对简单类型的属性
                    if (isComplexType(type)) {
                        return;
                    }
                    String name = field.getName();
                    fieldNames.add(name);
                });
        return fieldNames;
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
