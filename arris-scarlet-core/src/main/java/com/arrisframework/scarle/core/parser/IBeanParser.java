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

import com.arrisframework.scarle.core.bean.Bean;

/**
 * Bean主体 解析接口， 将table解析成 Bean
 *
 * @author arris
 * @version C01 2016-2-4
 */
public interface IBeanParser {

    /**
     * 解析表字段
     *
     * @author arris
     * @param content 表字段内容
     * @return 字段数据
     */
    public List<Bean> parse(String content);

}
