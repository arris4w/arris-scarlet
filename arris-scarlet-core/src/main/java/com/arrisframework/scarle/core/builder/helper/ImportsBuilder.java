/*
 * 文  件  名：ImportsBuilder.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-6-3
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.builder.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.arrisframework.common.ListUtils;
import com.arrisframework.scarle.core.utils.ScarleConstants;

/**
 * Import 块处理
 *
 * @author arris
 * @version C01 2015-6-3
 */
public class ImportsBuilder {
    /**
     * 特殊处理包集合
     */
    static List<String> specials = new ArrayList<String>();

    /**
     * 初始化特殊处理包
     */
    static {
        specials.add("java");
        specials.add("javax");
        specials.add("org");
    }

    /**
     * 处理 类引用
     *
     * @author arris
     * @param imports 类引用
     * @return 类引用
     */
    public static String process(List<String> imports) {
        if (ListUtils.isEmpty(imports)) {
            return null;
        }
        Collections.sort(imports);
        String template = "import %s;";

        StringBuffer sb = new StringBuffer();

        List<String> tempList = new ArrayList<String>();
        String prefix = null;
        String temp = "";
        for (int i = 0; i < imports.size(); i++) {
            temp = imports.get(i);
            prefix = temp.substring(0, temp.indexOf('.'));

            if (specials.contains(prefix)) {
                tempList.add(imports.remove(i));
                i--;
            }
        }

        String special = null;
        for (int i = specials.size() - 1; i >= 0; i--) {
            special = specials.get(i);
            for (int j = tempList.size() - 1; j >= 0; j--) {
                temp = tempList.get(j);
                prefix = temp.substring(0, temp.indexOf('.'));
                if (special.equals(prefix)) {
                    imports.add(0, tempList.remove(j));
                    // j++;
                }
            }
        }

        String prePrefix = null;
        String curPrefix = null;
        for (String string : imports) {
            curPrefix = string.substring(0, string.indexOf('.'));
            if (!curPrefix.equals(prePrefix)) {
                sb.append(ScarleConstants.LINE_SEPARATOR);
            }

            sb.append(String.format(template, string));
            sb.append(ScarleConstants.LINE_SEPARATOR);

            prePrefix = curPrefix;
        }

        return sb.toString();
    }
}
