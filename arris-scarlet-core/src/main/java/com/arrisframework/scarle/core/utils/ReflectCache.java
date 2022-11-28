/*
 * 文  件  名：ReflectCache.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2016-3-8
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 反射缓存
 *
 * @author arris
 * @version C01 2016-3-8
 */
public class ReflectCache {

    /**
     * 类对象缓存
     */
    private static Map<String, Object> objectMap = new HashMap<String, Object>();

    /**
     * 根据对象key 获取 对象value
     *
     * @author arris
     * @param key key
     * @return value
     */
    public static Object get(String key) {
        return objectMap.get(key);
    }

    /**
     * 设置对象键值
     *
     * @author arris
     * @param key key
     * @param object 对象
     * @return object
     */
    public static synchronized Object set(String key, Object object) {
        return objectMap.put(key, object);
    }
}
