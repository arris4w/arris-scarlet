/*
 * 文  件  名：Underline2CamelCaseNameConverter.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2016-3-8
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.converter.impl;

import com.arrisframework.common.StringUtils;
import com.arrisframework.scarle.core.utils.camel.CamelCaseUtils;

/**
 * 下划线字符转驼峰
 *
 * @author arris
 * @version C01 2016-3-8
 */
public class Underline2CamelCaseNameConverter extends AbstractNameConverter {

    /**
     * {@inheritDoc}
     */
    public String cleanPrefix(String str) {
        return str;
    }

    /**
     * {@inheritDoc}
     */
    public String s2Camel(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }

        return CamelCaseUtils.toCamelCase(str);
    }

    /**
     * {@inheritDoc}
     */
    public String s2UpperCamel(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }

        return CamelCaseUtils.toFWUpperCamelCase(s2Camel(str));
    }

    /**
     * {@inheritDoc}
     */
    public String s2LowerCamel(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }

        return CamelCaseUtils.toFWLowerCamelCase(s2Camel(str));
    }

    /**
     * {@inheritDoc}
     */
    public String s2Alias(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }

        return CamelCaseUtils.toAlias(str);
    }
}
