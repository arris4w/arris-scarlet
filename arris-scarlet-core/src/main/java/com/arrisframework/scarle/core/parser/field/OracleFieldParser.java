/*
 * 文  件  名：FieldParser.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2016-2-1
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.parser.field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
 * @version C01 2016-2-1
 */
public class OracleFieldParser extends AbstractFieldParser {

    /**
     * 在建表语句后的key
     */
    public static final Pattern APPEND_KEY = Pattern.compile("(^([a-z A-Z _ \\s+]+)\\s[\\w]+)(\\s+primary\\s+key\\s*)(\\(\\u0022?([\\w]+)\\u0022?\\))$");

    /**
     * {@inheritDoc}
     */
    public Map<String, List<Field>> parse(String[] contents) {
        String[] temps = null;

        String tableContent = contents[0];

        String keysContent = null;

        String temp = null;
        List<String> commentContentList = new ArrayList<String>();
        for (int i = 1; i < contents.length; i++) {
            temp = contents[i].trim();
            if (temp.indexOf("comment on column") >= 0) {
                commentContentList.add(temp.replaceAll(ScarleConstants.LINE_SEPARATOR, " "));
            } else if (temp.indexOf("primary key") >= 0) {
                keysContent = temp;
            }
        }

        Map<String, String> commentMap = parseComment(commentContentList);

        if (tableContent.indexOf("\r\n)") >= 0) {
            tableContent = tableContent.substring(0, tableContent.indexOf("\r\n)") + 3);
        }
        tableContent = tableContent.substring(tableContent.indexOf("(") + 1, tableContent.lastIndexOf(")"));

        temps = tableContent.split(",\\D");
        List<String> tableContentList = new ArrayList<String>();
        for (int i = 0; i < temps.length; i++) {
            temp = temps[i].trim();
            if (APPEND_KEY.matcher(temp).matches()) {
                keysContent = temp;
            } else if (temp.indexOf("primary key") >= 0) {
                keysContent = temp;
                tableContentList.add(temp);
            } else {
                tableContentList.add(temp);
            }
        }

        List<String> keys = parseKey(keysContent);

        Name name = null;
        String type = null;
        String comment = null;
        Field field = null;

        List<Field> fields = new ArrayList<Field>();
        List<Field> identifys = new ArrayList<Field>();

        boolean isIdentify = false;
        for (int i = 0; i < tableContentList.size(); i++) {
            temp = tableContentList.get(i);
            temp = temp.replaceAll("\\s{1,}", " ");
            temps = temp.split(" ");

            name = parseName(temps[0]);
            type = parseType(temps[1]);

            if (commentMap.containsKey(name.getOriginal().toLowerCase())) {
                comment = commentMap.get(name.getOriginal().toLowerCase());
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
     * {@inheritDoc}
     */
    public List<String> parseKey(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }

        List<String> keys = new ArrayList<String>();
        String key = "primary key";
        if (APPEND_KEY.matcher(content).matches()) {
            content = content.substring(content.indexOf("(") + 1, content.indexOf(")"));
            if (content.indexOf(',') > 0) {
                String[] temps = content.split(",");
                for (String string : temps) {
                    keys.add(ParserUtils.clean(string.trim(), "\"", " ").toLowerCase());
                }
            } else {
                keys.add(ParserUtils.clean(content.trim(), "\"").toLowerCase());
            }
            return keys;
        } else if (content.indexOf(key) >= 0) {
            content = content.substring(0, content.indexOf(" ") + 1);
            keys.add(ParserUtils.clean(content.trim(), "\"").toLowerCase());
            return keys;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Name parseName(String content) {
        content = ParserUtils.clean(content, "\"", " ");
        return new Name(content);
    }

    /**
     * {@inheritDoc}
     */
    public String parseType(String content) {
        return super.parseType(content, ScarleTypeCache.ORACLE);
    }

    /**
     * 字段注释解析
     *
     * @author arris
     * @param commentContentList 字段注释内容列表
     * @return 字段注释键值对
     */
    private Map<String, String> parseComment(List<String> commentContentList) {
        String[] temps;
        String temp;
        Map<String, String> commentMap = new HashMap<String, String>();
        for (int i = 0; i < commentContentList.size(); i++) {
            temp = commentContentList.get(i);

            temp = temp.split("\\.")[1];
            temps = temp.split(" is ");

            String key = ParserUtils.clean(temps[0], "\"", " ", "'");
            String value = ParserUtils.clean(temps[1], "\"", " ", "'");

            commentMap.put(key, value);
        }
        return commentMap;
    }

}
