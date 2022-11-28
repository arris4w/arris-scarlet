/*
 * 文  件  名：NonFeature2CamelCaseNameConverter.java
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
 * 无特征原字符转驼峰
 *
 * @author arris
 * @version C01 2016-3-8
 */
public class NonFeature2CamelCaseNameConverter extends AbstractNameConverter {

    /**
     * {@inheritDoc}
     */
    public String s2Camel(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }

        if (str.indexOf("_") > 0) {
            StringBuilder sb = new StringBuilder();
            String temp[] = str.split("_");
            sb.append(temp[0]);

            for (int i = 1; i < temp.length; i++) {
                sb.append(CamelCaseUtils.toCapitalize(temp[i]));
            }

            return sb.toString();
        } else {
            return str;
        }
    }

    /**
     * {@inheritDoc}
     */
    public String s2UpperCamel(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * {@inheritDoc}
     */
    public String s2LowerCamel(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }

        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * {@inheritDoc}
     */
    public String s2Alias(String str) {
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
}
