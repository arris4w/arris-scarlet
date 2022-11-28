/*
 * 文  件  名：AbstractEngine.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-5-19
 * 修改内容：新增
 */
package com.arrisframework.scarle.core;

import java.util.List;

import com.arrisframework.common.ListUtils;
import com.arrisframework.common.StringUtils;
import com.arrisframework.scarle.core.bean.Bean;
import com.arrisframework.scarle.core.builder.IBuilder;
import com.arrisframework.scarle.core.builder.pure.BeanBuilder;
import com.arrisframework.scarle.core.builder.pure.MapperBuilder;
import com.arrisframework.scarle.core.builder.pure.ProjectBuilder;
import com.arrisframework.scarle.core.builder.pure.RepositoryBuilder;
import com.arrisframework.scarle.core.builder.pure.ServiceImplementBuilder;
import com.arrisframework.scarle.core.builder.pure.ServiceInterfaceBuilder;
import com.arrisframework.scarle.core.utils.ScarleCache;
import com.arrisframework.scarle.core.utils.TemplateCache;
import com.arrisframework.scarle.core.utils.exception.ScarleException;
import com.arrisframework.scarle.core.utils.file.FileLoader;

/**
 * 抽象语法解析生成引擎
 *
 * @author arris
 * @version C01 2016-2-2
 */
public abstract class AbstractEngine {

    /**
     * 业务处理
     *
     * @author arris
     * @param content SQL 文本内容
     */
    public void service(String content) {
        FileLoader loader = FileLoader.getInstance();

        loadTemplate(loader);

        List<Bean> beans = parse(content);

        build(beans);
    }

    /**
     * 具体语法解析
     *
     * @author arris
     * @param content SQL 文本内容
     * @return 转换后的Bean 对象列表
     */
    public abstract List<Bean> parse(String content);

    /**
     * 模板文件初始化
     *
     * @author arris
     * @param loader 文件加载对象
     */
    protected void loadTemplate(FileLoader loader) {
        String path = ScarleCache.get("target.template.path");
        if (StringUtils.isEmpty(path)) {
            throw new ScarleException("Unkown target template file root path [" + path + "].");
        }
        TemplateCache.set("exception.template", loader.load(path + "/project/exception.template"));
        TemplateCache.set("resultCode.template", loader.load(path + "/project/resultCode.template"));
        TemplateCache.set("context.template", loader.load(path + "/project/context.template"));
        TemplateCache.set("context.listener.template", loader.load(path + "/project/context.listener.template"));
        TemplateCache.set("bean.template", loader.load(path + "/bean.template"));
        TemplateCache.set("field.template", loader.load(path + "/field.template"));
        TemplateCache.set("getsets.template", loader.load(path + "/getsets.template"));

        TemplateCache.set("db.repository.template", loader.load(path + "/db/repository.template"));
        TemplateCache.set("db.deletes.template", loader.load(path + "/db/deletes.template"));
        TemplateCache.set("db.mapper.template", loader.load(path + "/db/mapper/mapper.template"));
        TemplateCache.set("db.mapper.deletes.template", loader.load(path + "/db/mapper/deletes.template"));

        TemplateCache.set("service.template", loader.load(path + "/service/service.template"));
        TemplateCache.set("service.delete.template", loader.load(path + "/service/service.delete.template"));
        TemplateCache.set("service.impl.template", loader.load(path + "/service/impl/service.impl.template"));
        TemplateCache.set("service.impl.delete.template",
                loader.load(path + "/service/impl/service.impl.delete.template"));
    }

    /**
     * Java 文件构建
     *
     * @author arris
     * @param beans bean对象列表
     */
    protected void build(List<Bean> beans) {
        if (ListUtils.isEmpty(beans)) {
            return;
        }

        IBuilder builder = new ProjectBuilder(new BeanBuilder(new RepositoryBuilder(new MapperBuilder(
                new ServiceInterfaceBuilder(new ServiceImplementBuilder())))));
        builder.process(beans);
    }
}
