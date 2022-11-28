/*
 * 文  件  名：ScarleConstants.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-5-21
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.utils;

import java.util.regex.Pattern;

/**
 * Scarle 常量类
 *
 * @author arris
 * @version C01 2015-5-21
 */
public interface ScarleConstants {

    /**
     * scarle.prop
     */
    String SCARLE_PROP = "scarle.prop";

    /**
     * 下划线
     */
    char UNDERLINE = '_';

    /**
     * 操作系统换行符
     */
    String LINE_SEPARATOR = System.getProperty("line.separator", "\n");

    /**
     * 正则表达式转义字符
     */
    Pattern REG_CHAR = Pattern.compile("([\\{\\}\\[\\]\\(\\)\\^\\$\\.\\*\\?\\-\\+\\\\])");
}
