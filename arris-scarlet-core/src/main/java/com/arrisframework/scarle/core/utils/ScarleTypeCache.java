/*
 * 文  件  名：ScarleTypeCache.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-5-19
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.utils;


/**
 * 数据类型 与 Java 类型映射
 *
 * @author arris
 * @version C01 2015-5-19
 */
public class ScarleTypeCache {

    /**
     * Mysql
     */
    public static final String MYSQL = "MySQL";

    /**
     * Oracle
     */
    public static final String ORACLE = "Oracle";

    /**
     * 类型配置文件
     */
    public static final String TYPE_FILE = "scarle.type.%s.prop";

    /**
     * 获取数据库数据类型对应Java类型
     *
     * @author arris
     * @param sqlType 数据库数据类型
     * @param rdbms 数据库类型
     * @return Java类型
     */
    public static String getType(String sqlType, String rdbms) {
        if (null == sqlType || "".equals(sqlType.trim())) {
            return null;
        }
        if (null == rdbms || "".equals(rdbms.trim())) {
            return null;
        }
        return ScarleCache.get(String.format(TYPE_FILE, rdbms.toLowerCase()), sqlType.toLowerCase());
    }
}
