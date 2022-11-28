/*
 * 文  件  名：INameConverter.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2016-3-8
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.converter;

/**
 * 名称（类名、属性名、原始名、表别名）转换处理器
 *
 * @author arris
 * @version C01 2016-3-8
 */
public interface INameConverter {

    /**
     * 清理前缀
     *
     * @author arris
     * @param str 待转清理符串
     * @return 清理后的字符串
     */
    String cleanPrefix(String str);

    /**
     * 驼峰处理
     *
     * @author arris
     * @param str 待转换字符串
     * @return 转换后的字符串
     */
    String s2Camel(String str);

    /**
     * 原命名处理；一般直接返回
     *
     * @author arris
     * @param str 待转换字符串
     * @return 转换后的字符串
     */
    String s2Original(String str);

    /**
     * 首字母大写驼峰处理
     *
     * @author arris
     * @param str 待转换字符串
     * @return 转换后的字符串
     */
    String s2UpperCamel(String str);

    /**
     * 首字母小写写驼峰处理
     *
     * @author arris
     * @param str 待转换字符串
     * @return 转换后的字符串
     */
    String s2LowerCamel(String str);

    /**
     * 原命名对应别名处理
     *
     * @author arris
     * @param str 待转换字符串
     * @return 转换后的字符串
     */
    String s2Alias(String str);

}
