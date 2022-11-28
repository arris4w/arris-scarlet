/*
 * 文  件  名：OracleBeanParser.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间： 2016-2-1
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.parser.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.arrisframework.common.StringUtils;
import com.arrisframework.scarle.core.bean.Bean;
import com.arrisframework.scarle.core.bean.Field;
import com.arrisframework.scarle.core.bean.Name;
import com.arrisframework.scarle.core.parser.IFieldParser;
import com.arrisframework.scarle.core.parser.ParserUtils;
import com.arrisframework.scarle.core.utils.ScarleConstants;

/**
 * Oracle 语法解析， 将table解析成 Bean
 *
 * @author arris
 * @version C01 2016-2-1
 */
public class OracleBeanParser extends AbstractBeanParser {

    /**
     * 构造函数
     *
     * @param filedfParser 字段解析器对象
     */
    public OracleBeanParser(IFieldParser filedfParser) {
        super.fieldParser = filedfParser;
    }

    /**
     * {@inheritDoc}
     */
    public List<Bean> parse(String content) {
        String[] tables = content.split("(create table |CREATE TABLE )");
        String[] lines = null;

        Name name = null;
        String comment;
        Map<String, List<Field>> map = null;

        Bean bean = null;
        List<Bean> list = new ArrayList<Bean>();
        for (int i = 1; i < tables.length; i++) {
            lines = tables[i].split(";");
            name = parseName(lines[0]);
            comment = parseComment(name.getOriginal(), lines);
            map = fieldParser.parse(lines);
            bean = new Bean(name, comment, map.get("identifys"), map.get("fields"));
            list.add(bean);
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    public Name parseName(String content) {
        content = content.replaceAll(ScarleConstants.LINE_SEPARATOR, " ");
        content = content.substring(0, content.indexOf('('));
        content = ParserUtils.clean(content, "\"", " ");
        return new Name(content);
    }

    /**
     * {@inheritDoc}
     */
    public String parseComment(String tableName, String[] lines) {
        if (StringUtils.isEmpty(tableName)) {
            return null;
        }

        if (lines.length <= 0) {
            return null;
        }

        String content = lines[1];
        content = content.replaceAll(ScarleConstants.LINE_SEPARATOR, " ");

        String start = "comment on table";
        if (content.trim().startsWith(start)) {
            content = content.substring(content.indexOf('\'') + 1, content.lastIndexOf('\''));
            return content;
        }
        return "TODO";
    }
}
