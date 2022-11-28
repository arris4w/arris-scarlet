/*
 * 文  件  名：TemplateCache.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-5-19
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 模板缓存
 * 
 * @author arris
 * @version C01 2015-5-19
 */
public class TemplateCache {
    /**
     * 模板数据缓存
     */
    private static Map<String, String> templateMap = new HashMap<String, String>();

    /**
     * 根据 模板key 获取 模板value
     * 
     * @author arris
     * @param key key
     * @return value
     */
    public static String get(String key) {
        return templateMap.get(key);
    }

    /**
     * 设置模板键值
     * 
     * @author arris
     * @param key key
     * @param template 模板
     * @return template
     */
    public static String set(String key, String template) {
        return templateMap.put(key, template);
    }
}
