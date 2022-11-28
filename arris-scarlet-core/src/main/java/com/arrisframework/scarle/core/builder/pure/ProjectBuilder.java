/*
 * 文  件  名：ProjectBuilder.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-6-2
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.builder.pure;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.arrisframework.scarle.core.bean.Bean;
import com.arrisframework.scarle.core.builder.AbstractBuilder;
import com.arrisframework.scarle.core.builder.IBuilder;
import com.arrisframework.scarle.core.parser.ParserUtils;
import com.arrisframework.scarle.core.utils.ScarleCache;
import com.arrisframework.scarle.core.utils.TemplateCache;
import com.arrisframework.scarle.core.utils.file.FileGenerator;

/**
 * 工程名相关创建
 *
 * @author arris
 * @version C01 2015-6-2
 */
public class ProjectBuilder extends AbstractBuilder {

    /**
     * 日志对象
     */
    // private static final Logger log = LoggerFactory.getLogger(ProjectBuilder.class);

    /**
     * 构造函数
     */
    public ProjectBuilder() {
    }

    /**
     * 构造函数
     *
     * @param builder builder
     */
    public ProjectBuilder(IBuilder builder) {
        super(builder);
    }

    /**
     * {@inheritDoc}
     */
    public void process(List<Bean> beans) {
        if (null != builder) {
            builder.process(beans);
        }

        Map<String, String> tempMap = new HashMap<String, String>();
        tempMap.put("Exception", TemplateCache.get("exception.template"));
        tempMap.put("Context", TemplateCache.get("context.template"));
        tempMap.put("ContextListener", TemplateCache.get("context.listener.template"));

        tempMap.put("ResultCode", TemplateCache.get("resultCode.template"));

        Set<Entry<String, String>> set = tempMap.entrySet();
        Iterator<Entry<String, String>> it = set.iterator();

        String template = null;
        String content = null;
        while (it.hasNext()) {
            Entry<String, String> entry = it.next();
            template = entry.getValue();

            content = ParserUtils.format(template, "common.projectName", ScarleCache.get("common.projectName"));
            content = ParserUtils.format(content, "common.package", ScarleCache.get("common.package"));
            content = ParserUtils.format(content, "common.user", ScarleCache.get("common.user"));
            content = ParserUtils.format(content, "common.date", ScarleCache.get("common.date"));

            if ("Exception".equals(entry.getKey())) {
                FileGenerator.save(content, ScarleCache.get("common.package") + ".utils.exception",
                        ScarleCache.get("common.projectName") + entry.getKey() + ".java");
            } else if ("ResultCode".equals(entry.getKey())) {
                FileGenerator.save(content, ScarleCache.get("common.package") + ".utils.exception", entry.getKey()
                        + ".java");
            } else {
                FileGenerator.save(content, ScarleCache.get("common.package") + ".system.context",
                        ScarleCache.get("common.projectName") + entry.getKey() + ".java");
            }
        }
    }
}
