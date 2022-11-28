/*
 * 文  件  名：Name.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-5-19
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.bean;

import com.arrisframework.common.StringUtils;
import com.arrisframework.scarle.core.converter.INameConverter;
import com.arrisframework.scarle.core.utils.ReflectCache;
import com.arrisframework.scarle.core.utils.ScarleCache;

/**
 * 字段名称
 *
 * @author arris
 * @version C01 2015-5-19
 */
public class Name {

    /**
     * 表名称、字段名称
     */
    private String original;

    /**
     * 属性名称、类名称
     */
    private String upperCamel;

    /**
     * 属性名称、类名称
     */
    private String lowerCamel;

    /**
     * 表别名
     */
    private String alias;

    /**
     * 构造函数
     */
    public Name() {

    }

    /**
     * 构造函数
     *
     * @param original original
     */
    public Name(String original) {
        this.original = original;
        String temp = original;
        String prefix = ScarleCache.get("common.tablePrefix");
        if (!StringUtils.isEmpty(prefix)) {
            if (temp.startsWith(prefix)) {
                temp = temp.substring(prefix.length());
            }
        }

        String converterKey = ScarleCache.get("name.converter");
        INameConverter converter = (INameConverter) ReflectCache.get(converterKey);

        temp = converter.cleanPrefix(temp);
        this.upperCamel = converter.s2UpperCamel(temp);
        this.lowerCamel = converter.s2LowerCamel(temp);
        this.alias = converter.s2Alias(temp);
    }

    /**
     * 取得original
     *
     * @return 返回original。
     */
    public String getOriginal() {
        return original;
    }

    /**
     * 取得upperCamel
     *
     * @return 返回upperCamel。
     */
    public String getUpperCamel() {
        return upperCamel;
    }

    /**
     * 取得lowerCamel
     *
     * @return 返回lowerCamel。
     */
    public String getLowerCamel() {
        return lowerCamel;
    }

    /**
     * 取得alias
     *
     * @return 返回alias。
     */
    public String getAlias() {
        return alias;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return original + "|" + alias + "|" + upperCamel + "|" + lowerCamel;
    }

}
