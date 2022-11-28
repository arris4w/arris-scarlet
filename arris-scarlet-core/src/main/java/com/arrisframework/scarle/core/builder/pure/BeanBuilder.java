/*
 * 文  件  名：BeanBuilder.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-5-21
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.builder.pure;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arrisframework.common.StringUtils;
import com.arrisframework.scarle.core.bean.Bean;
import com.arrisframework.scarle.core.bean.Field;
import com.arrisframework.scarle.core.builder.AbstractBuilder;
import com.arrisframework.scarle.core.builder.IBuilder;
import com.arrisframework.scarle.core.builder.helper.ImportsBuilder;
import com.arrisframework.scarle.core.parser.ParserUtils;
import com.arrisframework.scarle.core.utils.ScarleCache;
import com.arrisframework.scarle.core.utils.ScarleConstants;
import com.arrisframework.scarle.core.utils.TemplateCache;
import com.arrisframework.scarle.core.utils.TemplateConstants;
import com.arrisframework.scarle.core.utils.file.FileGenerator;

/**
 * Bean文件 创建
 *
 * @author arris
 * @version C01 2015-5-21
 */
public class BeanBuilder extends AbstractBuilder {

    /**
     * 日志对象
     */
    private static final Logger log = LoggerFactory.getLogger(BeanBuilder.class);

    /**
     * 构造函数
     */
    public BeanBuilder() {
    }

    /**
     * 构造函数
     *
     * @param builder builder
     */
    public BeanBuilder(IBuilder builder) {
        super(builder);
    }

    /**
     * {@inheritDoc}
     */
    public void process(List<Bean> beans) {
        if (null != builder) {
            builder.process(beans);
        }

        String beanTemp = TemplateCache.get("bean.template");
        String fieldTemp = TemplateCache.get("field.template");
        String getsetsTemp = TemplateCache.get("getsets.template");

        for (Bean bean : beans) {
            try {
                StringBuffer fieldSb = new StringBuffer();
                StringBuffer getsetSb = new StringBuffer();

                String fieldContent = null;
                String getsetContent = null;

                List<String> imports = new ArrayList<String>();
                List<Field> identifys = bean.getIdentifys();
                for (Field field : bean.getFields()) {
                    String type = field.getType();
                    type = getType(imports, type);

                    fieldContent = ParserUtils.format(fieldTemp, "field.comment", field.getComment());
                    fieldContent = ParserUtils.format(fieldContent, "field.type", type);
                    fieldContent = ParserUtils.format(fieldContent, "field.name", field.getName().getLowerCamel());

                    getsetContent = ParserUtils.format(getsetsTemp, "field.name", field.getName().getLowerCamel());
                    getsetContent = ParserUtils.format(getsetContent, "field.name.warp",
                            field.getName().getUpperCamel());
                    getsetContent = ParserUtils.format(getsetContent, "field.type", type);
                    getsetContent = ParserUtils.format(getsetContent, "field.methodSufix",
                            field.getName().getUpperCamel());

                    fieldSb.append(fieldContent);
                    getsetSb.append(getsetContent);
                }

                fieldContent = fieldSb.toString();
                getsetContent = getsetSb.toString();

                String beanContent = ParserUtils.format(beanTemp, "bean.name", bean.getName().getUpperCamel());
                beanContent = ParserUtils.format(beanContent, "bean.comment", bean.getComment());
                beanContent = ParserUtils.format(beanContent, "common.user", ScarleCache.get("common.user"));
                beanContent = ParserUtils.format(beanContent, "common.package", ScarleCache.get("common.package"));
                beanContent = ParserUtils.format(beanContent, "common.date", ScarleCache.get("common.date"));

                beanContent = processConstruct(identifys, beanContent);

                String importContent = ImportsBuilder.process(imports);
                if (StringUtils.isEmpty(importContent)) {
                    importContent = "";
                }

                beanContent = ParserUtils.format(beanContent, "import", importContent);

                beanContent = ParserUtils.format(beanContent, "fields", fieldContent);
                beanContent = ParserUtils.format(beanContent, "getsets", getsetContent);

                FileGenerator.save(beanContent, ScarleCache.get("common.package") + ".model",
                        bean.getName().getUpperCamel() + ".java");
            } catch (Exception e) {
                log.error("Generator bean [" + bean.getName().getUpperCamel() + "] has exception.", e);
            }
        }
    }

    /**
     * 处理Bean 构造函数块
     *
     * @author arris
     * @param identifys 主键数据
     * @param beanContent Bean 当前内容
     * @return Bean 当前内容
     */
    private static String processConstruct(List<Field> identifys, String beanContent) {
        String comment = "";
        String param = "";
        String content = "";
        for (Field field : identifys) {
            String type = field.getType();
            type = getType(null, type);

            // 处理构造方法注释
            if (!StringUtils.isEmpty(comment)) {
                comment += ScarleConstants.LINE_SEPARATOR + "     ";
            }
            comment += String.format(TemplateConstants.Construct.COMMENT, field.getName().getLowerCamel(),
                    field.getComment());

            // 处理构造方法参数
            if (!StringUtils.isEmpty(param)) {
                param += ", ";
            }
            param += String.format(TemplateConstants.Construct.PARAM, type, field.getName().getLowerCamel());

            // 处理构造方法内容体
            if (!StringUtils.isEmpty(content)) {
                content += ScarleConstants.LINE_SEPARATOR + "        ";
            }
            content += String.format(TemplateConstants.Construct.CONTENT, field.getName().getLowerCamel(),
                    field.getName().getLowerCamel());

        }

        beanContent = ParserUtils.format(beanContent, "construct.comment", comment);
        beanContent = ParserUtils.format(beanContent, "construct.param", param);
        beanContent = ParserUtils.format(beanContent, "construct.content", content);

        return beanContent;
    }

    /**
     * 获取 Bean 属性类型
     *
     * @author arris
     * @param imports 类型应用列表，如果当前类型需要 import 则会加入 imports
     * @param type 属性类型完整名称；java.util.Date
     * @return 属性类型； Date
     */
    private static String getType(List<String> imports, String type) {
        String result = type;
        if (type.lastIndexOf('.') > 0) {
            if (null != imports) {
                if (!type.startsWith("java.lang") && !imports.contains(type)) {
                    imports.add(type);
                }
            }
            result = type.substring(type.lastIndexOf('.') + 1, type.length());
        }

        return result;
    }
}
