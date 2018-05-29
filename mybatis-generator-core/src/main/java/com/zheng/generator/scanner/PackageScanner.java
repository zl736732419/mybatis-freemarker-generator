package com.zheng.generator.scanner;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.SystemPropertyUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 类扫描工具
 * 实现对指定包下的实体进行扫描，并获取到对应的class
 * @Author zhenglian
 * @Date 2018/5/29 0:09
 */
@Component
public class PackageScanner {
    /**
     * 根据包路径，获取包下所有的class字节码文件
     */
    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    /**
     * 读取字节码中的信息
     */
    private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);
    
    /**
     * 根据给定的包，扫描其下的所有类
     * @param packages
     * @return
     */
    public Set<Class> scan(String[] packages) {
        if (ArrayUtils.isEmpty(packages)) {
            return null;
        }
        Set<Class> classes = new HashSet<>();
        for (String pkg : packages) {
            classes.addAll(doScan(pkg));
        }
        
        return classes;
    }

    /**
     * 扫描以逗号分隔的包
     * @param packages
     * @return
     */
    public Set<Class> scan(String packages) {
        if (StringUtils.isEmpty(packages)) {
            return null;
        }
        String[] packageArrs = StringUtils.tokenizeToStringArray(packages, ",; \t\n");
        return scan(packageArrs);
    }

    /**
     * 扫描指定的包
     * @param pkg
     * @return
     */
    private Collection<? extends Class> doScan(String pkg) {
        if (StringUtils.isEmpty(pkg)) {
            return null;
        }

        Set<Class> classes = new HashSet<>();
        
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                + ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(pkg))
                + "/**/*.class";
        try {
            Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
            if (ArrayUtils.isEmpty(resources)) {
                return null;
            }
            
            for (Resource resource : resources) {
                if (!resource.isReadable()) {
                    continue;
                }

                MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                try {
                    classes.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new BeanDefinitionStoreException(
                    "I/O failure during classpath scanning", e);
        }

        return classes;
    }
}
