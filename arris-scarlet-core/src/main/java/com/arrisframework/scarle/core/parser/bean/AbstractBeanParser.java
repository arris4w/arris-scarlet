/*
 * 文  件  名：AbstractBeanParser.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2016-2-4
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.parser.bean;

import com.arrisframework.scarle.core.bean.Name;
import com.arrisframework.scarle.core.parser.IBeanParser;
import com.arrisframework.scarle.core.parser.IFieldParser;

/**
 * Bean主体 解析接口 抽象实现
 *
 * @author arris
 * @version C01 2016-2-4
 */
public abstract class AbstractBeanParser implements IBeanParser {

    /**
     * 字段解析器对象
     */
    protected IFieldParser fieldParser;

    /**
     * 解析Bean名称
     *
     * @author arris
     * @param content 目标内容
     * @return Bean 名称
     */
    public abstract Name parseName(String content);

    /**
     * 解析Bean注释
     * <p>
     * 需要兼容数据库语法，所以此处需要将 Table 相关的行都带过
     *
     * @author arris
     * @param tableName 表名称
     * @param lines Table 相关的行
     * @return Bean 注释
     */
    public abstract String parseComment(String tableName, String[] lines);

}
