/*
 * 文  件  名：OracleEngine.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2016-2-2
 * 修改内容：新增
 */
package com.arrisframework.scarle.core;

import java.util.List;

import com.arrisframework.scarle.core.bean.Bean;
import com.arrisframework.scarle.core.parser.IBeanParser;
import com.arrisframework.scarle.core.parser.bean.OracleBeanParser;
import com.arrisframework.scarle.core.parser.field.OracleFieldParser;

/**
 * Oracle 语法解析引擎
 *
 * @author arris
 * @version C01 2016-2-2
 */
public class OracleEngine extends AbstractEngine {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Bean> parse(String content) {
        IBeanParser beanParser = new OracleBeanParser(new OracleFieldParser());
        List<Bean> beans = beanParser.parse(content);
        return beans;
    }
}
