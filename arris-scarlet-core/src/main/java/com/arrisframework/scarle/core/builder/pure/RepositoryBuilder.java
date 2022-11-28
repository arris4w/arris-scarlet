/*
 * 文  件  名：BeanBuilder.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-5-21
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.builder.pure;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arrisframework.common.StringUtils;
import com.arrisframework.scarle.core.bean.Bean;
import com.arrisframework.scarle.core.bean.Field;
import com.arrisframework.scarle.core.builder.AbstractBuilder;
import com.arrisframework.scarle.core.builder.IBuilder;
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
public class RepositoryBuilder extends AbstractBuilder {

    /**
     * 日志对象
     */
    private static final Logger log = LoggerFactory.getLogger(RepositoryBuilder.class);

    /**
     * 构造函数
     */
    public RepositoryBuilder() {
    }

    /**
     * 构造函数
     *
     * @param builder builder
     */
    public RepositoryBuilder(IBuilder builder) {
        super(builder);
    }

    /**
     * 构建Repository文件
     *
     * @author arris
     * @param beans 表对应备案数据
     */
    public void process(List<Bean> beans) {
        if (null != builder) {
            builder.process(beans);
        }

        String dbRepositoryTemp = TemplateCache.get("db.repository.template");
        String dbDeletesTemp = TemplateCache.get("db.deletes.template");

        for (Bean bean : beans) {
            try {
                List<Field> identifys = bean.getIdentifys();

                String deletes = "";
                if (identifys.size() == 1) {
                    Field id = identifys.get(0);
                    deletes = ParserUtils.format(dbDeletesTemp, "bean.key.name", id.getName().getLowerCamel());
                    deletes = ParserUtils.format(deletes, "bean.key.type", getType(null, id.getType()));
                    deletes = ParserUtils.format(deletes, "bean.comment", bean.getComment());
                    deletes = ParserUtils.format(deletes, "common.user", ScarleCache.get("common.user"));
                }

                String content = ParserUtils.format(dbRepositoryTemp, "repository.deletes", deletes);

                content = ParserUtils.format(content, "bean.name", bean.getName().getUpperCamel());
                content = ParserUtils.format(content, "bean.pname", bean.getName().getLowerCamel());
                content = ParserUtils.format(content, "bean.comment", bean.getComment());
                content = ParserUtils.format(content, "common.user", ScarleCache.get("common.user"));
                content = ParserUtils.format(content, "common.package", ScarleCache.get("common.package"));
                content = ParserUtils.format(content, "common.date", ScarleCache.get("common.date"));

                content = processMethod(identifys, content);
                // content = processMethod(identifys, content);

                FileGenerator.save(content, ScarleCache.get("common.package") + ".dao", bean.getName().getUpperCamel()
                        + "Mapper.java");
            } catch (Exception e) {
                log.error("Generator repository [" + bean.getName().getUpperCamel() + "Repository] has exception.", e);
            }
        }
    }

    /**
     * 处理repository 方法块
     *
     * @author arris
     * @param identifys 主键数据
     * @param methodContent Bean 当前内容
     * @return repository 当前内容
     */
    private static String processMethod(List<Field> identifys, String methodContent) {
        String comment = "";
        String param = "";
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

        }

        methodContent = ParserUtils.format(methodContent, "method.comment", comment);
        methodContent = ParserUtils.format(methodContent, "method.param", param);

        return methodContent;
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
