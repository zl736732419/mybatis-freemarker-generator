package com.zheng.generator.formatter;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 驼峰命名法
 * 主要是为了实现首字母小写
 * @Author zhenglian
 * @Date 2018/5/29 22:08
 */
@Component
public class CamelFormatter extends Formatter {
    @Override
    public String format(String word) {
        if (StringUtils.isEmpty(word)) {
            return word;
        }
        
        char first = word.charAt(0);
        if (isUpperCaseChar(first)) {
            first = toLowerCase(first);
        }
        StringBuilder builder = new StringBuilder();
        builder.append(first)
                .append(word.substring(1));
        return builder.toString();
    }

    @Override
    public String parse(String word) {
        if (StringUtils.isEmpty(word)) {
            return word;
        }
        char first = word.charAt(0);
        if (isLowerCaseChar(first)) {
            first = toUpperCase(first);
        }
        StringBuilder builder = new StringBuilder();
        builder.append(first)
                .append(word.substring(1));
        return builder.toString();
    }

    @Override
    public String getName() {
        return "camel";
    }
}
