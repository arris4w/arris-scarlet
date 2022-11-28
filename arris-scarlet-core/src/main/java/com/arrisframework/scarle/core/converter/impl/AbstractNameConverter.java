/*
 * 文  件  名：AbstractNameConverter.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2016-3-8
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.converter.impl;

import com.arrisframework.common.StringUtils;
import com.arrisframework.scarle.core.converter.INameConverter;

/**
 * 抽象转换
 *
 * @author arris
 * @version C01 2016-3-8
 */
public abstract class AbstractNameConverter implements INameConverter {

    /**
     * {@inheritDoc}
     */
    public String s2Original(String str) {
        return str;
    }

    /**
     * {@inheritDoc}
     */
    public String cleanPrefix(String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }

        // TOTO 专题考虑
        return str.replaceFirst("^[a-zA-Z]{1,}_", "");
    }
}
