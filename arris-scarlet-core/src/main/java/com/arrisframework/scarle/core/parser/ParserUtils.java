/*
 * 文  件  名：ParserUtil.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-5-21
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.parser;

import com.arrisframework.common.StringUtils;
import com.arrisframework.scarle.core.utils.ScarleConstants;

/**
 * 解析工具类
 *
 * @author arris
 * @version C01 2015-5-21
 */
public class ParserUtils {
    /**
     * 格式化, 实现参数替换
     *
     * @author arris
     * @param string 原字符串
     * @param key 参数键
     * @param value 参数值
     * @return 参数替换后的字符串
     */
    public static String format(String string, String key, String value) {
        if (string == null || key == null || value == null) {
            return string;
        }
        String temp = key;
        if (temp.startsWith("{") && temp.endsWith("}")) {
            key = temp;
        } else {
            key = "${" + temp + "}";
        }
        // 替换正则表达式的转义字符, KEY不需要支持正则表达式
        // 如果不替换, user.id这个点就会成为通配符
        key = ScarleConstants.REG_CHAR.matcher(key).replaceAll("\\\\$1");

        string = string.replaceAll(key, value);
        return string;
    }

    /**
     * 字符串清理，正则表达式替换" [" 1] 替换 替换双引号、空格、单引号"
     *
     * @author arris
     * @param content 内容
     * @param specs 指定清理内容
     * @return 清理后内容
     */
    public static String clean(String content, String... specs) {
        if (StringUtils.isEmpty(content)) {
            return content;
        }

        if ((null == specs) || (specs.length == 0)) {
            return content;
        }

        StringBuffer regex = new StringBuffer("[");
        for (int i = 0; i < specs.length; i++) {
            if (null == specs[i]) {
                continue;
            }

            regex.append(specs[i]);
        }
        regex.append("]");
        return content.replaceAll(regex.toString(), "");
    }
}
