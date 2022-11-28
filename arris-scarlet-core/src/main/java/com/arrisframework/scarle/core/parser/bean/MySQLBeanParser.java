/*
 * 文  件  名：MySQLBeanParser.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-5-19
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
 * MySQL 语法解析， 将table解析成 Bean
 *
 * @author arris
 * @version C01 2015-5-19
 */
public class MySQLBeanParser extends AbstractBeanParser {

    /**
     * 构造函数
     *
     * @param filedfParser 字段解析器对象
     */
    public MySQLBeanParser(IFieldParser filedfParser) {
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
        content = ParserUtils.clean(content, "\"", "`", " ");
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

        if (lines.length > 1) {
            String content = lines[1];
            content = content.replaceAll(ScarleConstants.LINE_SEPARATOR, " ");
            String start = "alter table " + tableName + " comment ";
            if (content.trim().startsWith(start)) {
                content = content.substring(content.indexOf('\'') + 1, content.lastIndexOf('\''));
                return content;
            }
        }

        String content = lines[0];
        content = content.substring(content.lastIndexOf(")"));
        content = content.replaceAll(ScarleConstants.LINE_SEPARATOR, " ");
        content = content.trim();
        if (content.endsWith(")")) {
            return "TODO";
        }

        String[] targets = content.split(" ");
        for (String target : targets) {
            if (target.isEmpty() || target.indexOf("=") <= 0) {
                continue;
            }

            String[] temps = target.split("=");
            if (null != temps && null != temps[0]) {
                if (temps[0].toLowerCase().equals("comment")) {
                    return ParserUtils.clean(temps[1], "'", ";");
                }
            }
        }

        return "TODO";
    }
}
