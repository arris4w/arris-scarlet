/*
 * 文  件  名：TemplateConstants.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-5-21
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.utils;

/**
 * 常量模板
 *
 * @author arris
 * @version C01 2015-5-21
 */
public interface TemplateConstants {

    /**
     * 构造方法模板
     *
     * @author arris
     * @version C01 2015-5-21
     */
    interface Construct {
        /**
         * 注释
         */
        String COMMENT = "* @param %s %s";

        /**
         * 参数
         */
        String PARAM = "%s %s";

        /**
         * 内容
         */
        String CONTENT = "this.%s = %s;";

    }

    /**
     * 业务层模板
     *
     * @author arris
     * @version C01 2015-5-21
     */
    interface Service {
        /**
         * 日志模板
         *
         * @author arris
         * @version C01 2015-5-21
         */
        interface Log {
            /**
             * 参数校验日志
             */
            String JUDGE_COMMENT = "'%s'";

            /**
             * 异常日志部分
             */
            String ERROR_COMMENT = "'%s' is [\" + %s + \"]";

        }

        /**
         * 日志模板
         *
         * @author arris
         * @version C01 2015-5-21
         */
        interface Judge {
            /**
             * 字符串判断
             */
            String STRING = "StringUtils.isEmpty(%s)";

            /**
             * 数字类型判断
             */
            String NUMBER = "%s <= 0";
        }
    }

    /**
     * sql 模板
     *
     * @author arris
     * @version C01 2015-5-21
     */
    interface Sql {

        /**
         * 参数
         */
        String PARAM = "%s %s";

        /**
         * 内容
         */
        String CONTENT = "this.%s = %s;";
    }

}
