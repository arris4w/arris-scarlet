/*
 * 文  件  名：Test.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2016年4月15日
 * 修改内容：新增
 */
package com.arrisframework.scarle.core;

import java.util.regex.Pattern;

/**
 * TODO 添加类的描述
 *
 * @author arris
 * @version C01 2016年4月15日
 */
public class Test {

    public static void main(String[] args) {
        String targe = "constraint PK_ZJ_INVESTPROJECT primary key (\"sGuid\")";

        String targe1 = "constraint SYS_C0024351 pri mary key (SGUID)";
        String targe2 = "constraint SYS_C0024351 primary  (SGUID)";
        String targe3 = "constraint SYS_C0024351 primary   key (\"sGuid\")";
        String targe4 = "alter table SYS_C0024351 add constraint PK_CS_CURRENTTRADEDATE primary key (SGUID)";

        String regex = "(^([a-z A-Z _ \\s+]+)\\s[\\w]+)(\\s+primary\\s+key\\s*)(\\(\\u0022?([\\w]+)\\u0022?\\))$";
        Pattern DIGIT = Pattern.compile(regex);

        System.out.println(DIGIT.matcher(targe).matches());
        System.out.println(DIGIT.matcher(targe1).matches());
        System.out.println(DIGIT.matcher(targe2).matches());
        System.out.println(DIGIT.matcher(targe3).matches());
        System.out.println(DIGIT.matcher(targe4).matches());
    }
}
