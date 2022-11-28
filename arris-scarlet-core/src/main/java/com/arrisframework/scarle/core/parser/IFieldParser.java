/*
 * 文  件  名：IBeanParser.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2016-2-4
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.parser;

import java.util.List;
import java.util.Map;

import com.arrisframework.scarle.core.bean.Field;

/**
 * Table字段解析， 表字段解析成 bean 字段
 * 
 * @author arris
 * @version C01 2016-2-4
 */
public interface IFieldParser {

    /**
     * 解析表字段
     * 
     * @author arris
     * @param contents 表相关内容主体
     * @return 字段数据
     */
    Map<String, List<Field>> parse(String[] contents);
}
