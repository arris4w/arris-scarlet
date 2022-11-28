/*
 * 文  件  名：CommonMapping.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-5-19
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.utils;

import com.arrisframework.config.ConfigHelper;

/**
 * 通用初始映射
 *
 * @author arris
 * @version C01 2015-5-19
 */
public class ScarleCache {

    /**
     * 根据 key 获取 value
     *
     * @author arris
     * @param key key
     * @return value
     */
    public static String get(String key) {
        return get(ScarleConstants.SCARLE_PROP, key);
    }

    /**
     * 根据 key 获取 value
     *
     * @author arris
     * @param propKey 配置文件键
     * @param key key
     * @return value
     */
    public static String get(String propKey, String key) {
        return ConfigHelper.getValueFromSubProp(propKey, key);
    }
}
