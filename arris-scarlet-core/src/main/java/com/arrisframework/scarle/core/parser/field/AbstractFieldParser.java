/*
 * 文  件  名：AbstractFieldParser.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2016-2-4
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.parser.field;

import java.util.List;

import com.arrisframework.common.StringUtils;
import com.arrisframework.scarle.core.bean.Name;
import com.arrisframework.scarle.core.parser.IFieldParser;
import com.arrisframework.scarle.core.utils.ScarleTypeCache;

/**
 * 字段解析， 表字段解析成 bean 字段
 *
 * @author arris
 * @version C01 2016-2-4
 */
public abstract class AbstractFieldParser implements IFieldParser {

    /**
     * 解析主键
     *
     * @author arris
     * @param content 目标内容
     * @return 主键列表
     */
    public abstract List<String> parseKey(String content);

    /**
     * 解析名称
     *
     * @author arris
     * @param content 目标内容
     * @return 字段名称
     */
    public abstract Name parseName(String content);

    /**
     * 解析类型
     *
     * @author arris
     * @param content 目标内容
     * @return 字段类型
     */
    public abstract String parseType(String content);

    /**
     * 解析类型
     *
     * @author arris
     * @param content 目标内容
     * @param rdbms 数据库类型,TypeMapping.Oracle|TypeMapping.MySQL
     * @return 对应Java 语言类型
     */
    protected String parseType(String content, String rdbms) {
        String sqlType = content;
        String type = null;
        int precision = 0;
        int scale = 0;
        if (sqlType.indexOf('(') >= 0) {
            type = sqlType.substring(0, sqlType.indexOf('('));
            String temp = sqlType.substring(sqlType.indexOf('(') + 1, sqlType.indexOf(')'));

            sqlType = type;
            if (temp.indexOf(',') > 0) {
                String[] temps = temp.split(",");
                precision = Integer.valueOf(temps[0]);
                scale = Integer.valueOf(temps[1]);
            } else {
                precision = Integer.valueOf(temp);
            }
        }

        type = ScarleTypeCache.getType(sqlType, rdbms);
        if (StringUtils.isEmpty(type)) {
            return sqlType;
        }

        if (type.equals("double")) {
            if (0 == scale) {
                if (9 < precision) {
                    type = "long";
                } else {
                    type = "int";
                }
            }
        }

        return type;
    }

}
