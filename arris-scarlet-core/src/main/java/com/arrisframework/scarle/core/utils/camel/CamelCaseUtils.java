/*
 * 文  件  名：CamelCaseUtils.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-5-15
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.utils.camel;

import com.arrisframework.common.StringUtils;
import com.arrisframework.scarle.core.utils.ScarleConstants;

/**
 * 骆驼拼写法 工具类
 *
 * @author arris
 * @version C01 2015-5-15
 */
public class CamelCaseUtils {
    /**
     * 骆驼拼写法 转换成以下划线拼接
     *
     * @author arris
     * @param str 待转换字符串
     * @return 转换后的字符串
     */
    public static String toUnderline(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            boolean nextUpperCase = true;
            if (i < (str.length() - 1)) {
                nextUpperCase = Character.isUpperCase(str.charAt(i + 1));
            }

            if ((i >= 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    if (i > 0) {
                        sb.append(ScarleConstants.UNDERLINE);
                    }
                }
                upperCase = true;
            } else {
                upperCase = false;
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * 下划线拼接 转换成 骆驼拼写法
     *
     * @author arris
     * @param str 待转换字符串
     * @return 转换后的字符串
     */
    public static String toCamelCase(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }

        str = str.toLowerCase();
        StringBuilder sb = new StringBuilder(str.length());
        boolean upperCase = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == ScarleConstants.UNDERLINE) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线拼接 转换成 表别名
     *
     * @author arris
     * @param str 待转换字符串
     * @return 转换后的字符串
     */
    public static String toAlias(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }

        str = str.toLowerCase();
        String[] temps = str.split("_");
        StringBuilder sb = new StringBuilder(str.length());
        for (String temp : temps) {
            sb.append(temp.substring(0, 1));
        }

        return sb.toString();
    }

    /**
     * 首字母大写
     *
     * @author arris
     * @param str 待转换字符串
     * @return 转换后的字符串
     */
    public static String toFWUpperCamelCase(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 首字母小写
     *
     * @author arris
     * @param str 待转换字符串
     * @return 转换后的字符串
     */
    public static String toFWLowerCamelCase(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }

        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 首字母大写
     *
     * @author arris
     * @param str 待转换字符串
     * @return 转换后的字符串
     */
    public static String toCapitalize(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        str = toCamelCase(str);
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
