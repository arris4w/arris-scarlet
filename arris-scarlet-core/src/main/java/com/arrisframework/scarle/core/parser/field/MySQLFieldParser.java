/*
 * 文  件  名：FieldParser.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-5-19
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.parser.field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arrisframework.common.StringUtils;
import com.arrisframework.scarle.core.bean.Field;
import com.arrisframework.scarle.core.bean.Name;
import com.arrisframework.scarle.core.parser.ParserUtils;
import com.arrisframework.scarle.core.utils.ScarleConstants;
import com.arrisframework.scarle.core.utils.ScarleTypeCache;

/**
 * 字段解析， 表字段解析成 bean 字段
 *
 * @author arris
 * @version C01 2015-5-19
 */
public class MySQLFieldParser extends AbstractFieldParser {

    /**
     * 解析表字段
     *
     * @author arris
     * @param contents 表字段内容
     * @return 字段数据
     */
    public Map<String, List<Field>> parse(String[] contents) {
        String temp = null;
        String[] temps = null;
        String[] specs = null;

        String tableContent = contents[0];

        tableContent = tableContent.substring(tableContent.indexOf("(") + 1, tableContent.lastIndexOf(")"));
        tableContent = ParserUtils.clean(tableContent, ScarleConstants.LINE_SEPARATOR);
        tableContent = tableContent.replaceAll("\\s{1,}", " ");
        tableContent = tableContent.trim();

        String[] filedContents = tableContent.split(",\\D");

        String keysContent = filedContents[filedContents.length - 1];
        List<String> keys = parseKey(keysContent);

        Name name = null;
        String type = null;
        String comment = "TODO";
        Field field = null;

        List<Field> fields = new ArrayList<Field>();
        List<Field> identifys = new ArrayList<Field>();

        boolean isIdentify = false;
        for (int i = 0; i < filedContents.length - 1; i++) {
            temp = filedContents[i];
            temp = temp.trim();
            temps = temp.split(" comment | COMMENT ");
            specs = temps[0].split(" ");

            name = parseName(specs[0]);
            type = parseType(specs[1]);
            if (temps.length > 1) {
                comment = parseComment(temps[1]);
            } else {
                comment = "TODO";
            }

            isIdentify = keys.contains(name.getOriginal().toLowerCase());

            field = new Field(name, type, comment, isIdentify);
            if (isIdentify) {
                identifys.add(field);
            }

            fields.add(field);
        }

        Map<String, List<Field>> map = new HashMap<String, List<Field>>();
        map.put("fields", fields);
        map.put("identifys", identifys);

        return map;
    }

    /**
     * 解析主键
     *
     * @author arris
     * @param content 内容数组
     * @return 主键列表
     */
    public List<String> parseKey(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }

        content = content.trim();
        List<String> keys = new ArrayList<String>();

        String start = "primary key";
        if (content.startsWith(start) || content.startsWith(start.toUpperCase())) {
            content = content.substring(content.indexOf("(") + 1, content.lastIndexOf(")"));

            if (content.indexOf(',') > 0) {
                String[] temps = content.split(",");
                for (String string : temps) {
                    keys.add(ParserUtils.clean(string, "`", " ").toLowerCase());
                }
            } else {
                keys.add(ParserUtils.clean(content, "`", " ").toLowerCase());
            }
        }

        return keys;
    }

    /**
     * 解析名称
     *
     * @author arris
     * @param content 解析目标
     * @return 字段名称
     */
    public Name parseName(String content) {
        content = ParserUtils.clean(content, "`", " ");
        return new Name(content);
    }

    /**
     * 解析类型
     *
     * @author arris
     * @param content 解析目标
     * @return 字段类型
     */
    public String parseType(String content) {
        return super.parseType(content, ScarleTypeCache.MYSQL);
    }

    /**
     * 解析注释
     *
     * @author arris
     * @param comment 注释行
     * @return 注释信息
     */
    public String parseComment(String comment) {
        if (!StringUtils.isEmpty(comment)) {
            comment = comment.substring(comment.indexOf('\'') + 1, comment.lastIndexOf('\''));
            comment = ParserUtils.clean(comment, " ");
            return comment;
        }

        return "TODO";
    }
}
