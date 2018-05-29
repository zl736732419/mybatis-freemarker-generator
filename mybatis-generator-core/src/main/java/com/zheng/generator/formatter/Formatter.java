package com.zheng.generator.formatter;

/**
 * 单词格式化器
 * @Author zhenglian
 * @Date 2018/5/29 22:06
 */
public abstract class Formatter {
    /**
     * 传入一个单词，转化为另一种形式的单词
     * @param word
     * @return
     */
    abstract public String format(String word);

    /**
     * 格式化器在配置文件中的唯一标识
     * @return
     */
    abstract public String getName();

    /**
     * 是否是大写字母
     * @param c
     * @return
     */
    protected boolean isUpperCaseChar(char c) {
        return c>='A' && c <= 'Z';
    }

    /**
     * 变成小写字母
     * @param c
     * @return
     */
    protected char toLowerCase(char c) {
        return (char) (c + 32);
    }
}
