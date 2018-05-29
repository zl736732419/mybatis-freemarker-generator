package com.zheng.generator.formatter;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 单词之间通过下划线分割的形式
 * 这种形式通常用于数据库字段命名规范
 * @Author zhenglian
 * @Date 2018/5/29 22:14
 */
@Component
public class UnderscoreFormatter extends Formatter {
    private final String seperator = "_";
    
    @Override
    public String format(String word) {
        if (StringUtils.isEmpty(word)) {
            return word;
        }
        StringBuilder builder = new StringBuilder();
        char c;
        for (int i = 0; i < word.length(); i++) {
            c = word.charAt(i);
            if (isUpperCaseChar(c)) {
                c = toLowerCase(c);
                if (i != 0) {
                    builder.append(seperator);
                }
            }
            builder.append(c);
        }
        
        return builder.toString();
    }

    @Override
    public String getName() {
        return "underscore";
    }
}
