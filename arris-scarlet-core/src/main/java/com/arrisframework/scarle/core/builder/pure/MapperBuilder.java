/*
 * 文  件  名：MapperBuilder.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-5-30
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.builder.pure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arrisframework.scarle.core.bean.Bean;
import com.arrisframework.scarle.core.bean.Field;
import com.arrisframework.scarle.core.builder.AbstractBuilder;
import com.arrisframework.scarle.core.builder.IBuilder;
import com.arrisframework.scarle.core.parser.ParserUtils;
import com.arrisframework.scarle.core.utils.ScarleCache;
import com.arrisframework.scarle.core.utils.ScarleConstants;
import com.arrisframework.scarle.core.utils.TemplateCache;
import com.arrisframework.scarle.core.utils.file.FileGenerator;

/**
 * Mapper 文件创建
 *
 * @author arris
 * @version C01 2015-5-30
 */
public class MapperBuilder extends AbstractBuilder {

    /**
     * 日志对象
     */
    private static final Logger log = LoggerFactory.getLogger(MapperBuilder.class);

    /**
     * 构造函数
     */
    public MapperBuilder() {
    }

    /**
     * 构造函数
     *
     * @param builder builder
     */
    public MapperBuilder(IBuilder builder) {
        super(builder);
    }

    /**
     * {@inheritDoc}
     */
    public void process(List<Bean> beans) {
        if (null != builder) {
            builder.process(beans);
        }

        String mapperTemp = TemplateCache.get("db.mapper.template");
        String deleteTemp = TemplateCache.get("db.mapper.deletes.template");

        String commonPackage = ScarleCache.get("common.package");

        for (Bean bean : beans) {
            try {
                String mapperContent = ParserUtils.format(mapperTemp, "bean.name", bean.getName().getUpperCamel());
                mapperContent = ParserUtils.format(mapperContent, "bean.pname", bean.getName().getLowerCamel());
                mapperContent = ParserUtils.format(mapperContent, "bean.comment", bean.getComment());
                mapperContent = ParserUtils.format(mapperContent, "common.package", commonPackage);

                mapperContent = ParserUtils.format(mapperContent, "sql.insert", insert(bean));

                Map<String, String> map = delete(bean);

                mapperContent = ParserUtils.format(mapperContent, "sql.delete", map.get("sql.delete"));
                mapperContent = ParserUtils.format(mapperContent, "sql.isDelete", map.get("sql.isDelete"));

                mapperContent = ParserUtils.format(mapperContent, "sql.update", update(bean));
                mapperContent = ParserUtils.format(mapperContent, "sql.select", select(bean));

                mapperContent = ParserUtils.format(mapperContent, "sql.keyCondition", map.get("sql.keyCondition"));

                List<Field> identifys = bean.getIdentifys();

                if (identifys.size() == 1) {
                    mapperContent = ParserUtils.format(mapperContent, "bean.key.name.dbName", identifys.get(0).getName()
                            .getOriginal());

                    String deleteContent = ParserUtils.format(deleteTemp, "bean.comment", bean.getComment());
                    deleteContent = ParserUtils.format(deleteContent, "sql.isDelete", map.get("sql.isDelete"));
                    deleteContent = ParserUtils.format(deleteContent, "sql.deletePrefix", map.get("sql.deletePrefix"));

                    mapperContent = ParserUtils.format(mapperContent, "sql.deleteBatch", deleteContent);
                } else {
                    mapperContent = ParserUtils.format(mapperContent, "bean.key.name.dbName", "1");
                    mapperContent = ParserUtils.format(mapperContent, "sql.deleteBatch", "");
                }

                FileGenerator.save(mapperContent, commonPackage + ".dao.mapper", bean.getName().getUpperCamel()
                        + "Mapper.xml");
            } catch (Exception e) {
                log.error("Generator mapper [" + bean.getName().getUpperCamel() + "Mapper] has exception.", e);
            }
        }
    }

    /**
     * 构建 insert 语句
     *
     * @author arris
     * @param bean 表对应Bean
     * @return insert 语句
     */
    private static String insert(Bean bean) {
        StringBuffer insert = new StringBuffer("insert into ");
        insert.append(bean.getName().getOriginal());
        insert.append(" (");
        insert.append(ScarleConstants.LINE_SEPARATOR);
        insert.append("        ");

        List<Field> list = bean.getFields();
        Field temp = null;

        StringBuffer field = new StringBuffer();
        StringBuffer values = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            temp = list.get(i);
            field.append(temp.getName().getOriginal());
            values.append("#{");
            values.append(temp.getName().getLowerCamel());
            values.append("}");

            if (i != list.size() - 1) {
                field.append(",");
                values.append(",");

                field.append(" ");
                values.append(" ");
            }

            if (i != 0 && i % 5 == 0 && list.size() - 1 > 5) {
                field.append(ScarleConstants.LINE_SEPARATOR);
                field.append("        ");

                values.append(ScarleConstants.LINE_SEPARATOR);
                values.append("        ");
            }
        }
        insert.append(field);
        insert.append(")");
        insert.append(ScarleConstants.LINE_SEPARATOR);
        insert.append("        values (");
        insert.append(values);
        insert.append(")");

        return insert.toString();
    }

    /**
     * 创建 delete 语句
     *
     * @author arris
     * @param bean 表对应Bean
     * @return delete 语句
     */
    private static Map<String, String> delete(Bean bean) {
        StringBuffer update = new StringBuffer("update ");
        update.append(bean.getName().getOriginal());
        update.append(" set ");

        StringBuffer delele = new StringBuffer("delete from ");
        delele.append(bean.getName().getOriginal());
        delele.append(ScarleConstants.LINE_SEPARATOR);
        delele.append("        ");

        List<Field> list = bean.getFields();
        StringBuffer set = new StringBuffer();

        List<Field> identifys = bean.getIdentifys();
        Field temp = null;

        boolean isDelete = false;
        for (int i = 0; i < list.size(); i++) {
            temp = list.get(i);
            if (temp.isIdentify()) {
                continue;
            }

            if (temp.getName().getOriginal().equals("is_delete")) {
                set.append("is_delete = 1");
                isDelete = true;
            } else {
                continue;
            }

            if ((i != 0 && i % 3 == 0) || (i != 0 && i == list.size() - 1)) {
                set.append(ScarleConstants.LINE_SEPARATOR);
                set.append("        ");
            }
        }
        update.append(set);

        StringBuffer keyCondition = new StringBuffer("and ");
        StringBuffer deletePrefix = new StringBuffer();

        StringBuffer condition = new StringBuffer();

        if (identifys.size() > 0) {
            update.append("where ");
            delele.append("where ");

            for (int i = 0; i < identifys.size(); i++) {
                temp = identifys.get(i);

                if (isDelete) {
                    deletePrefix.append(update);
                } else {
                    deletePrefix.append(delele);
                }

                deletePrefix.append(temp.getName().getOriginal());
                deletePrefix.append(" in ");

                condition = new StringBuffer();
                condition.append(temp.getName().getOriginal());
                condition.append(" = #{");
                condition.append(temp.getName().getLowerCamel());
                condition.append("}");

                update.append(condition);
                delele.append(condition);

                keyCondition.append(bean.getName().getAlias());
                keyCondition.append(".");
                keyCondition.append(condition);

                if (i != identifys.size() - 1) {
                    update.append(" and ");
                    delele.append(" and ");
                    keyCondition.append(" and ");
                }
            }
        }

        Map<String, String> map = new HashMap<>();
        if (isDelete) {
            map.put("sql.isDelete", "update");
            map.put("sql.delete", update.toString());
        } else {
            map.put("sql.isDelete", "delete");
            map.put("sql.delete", delele.toString());
        }

        map.put("sql.keyCondition", keyCondition.toString());
        map.put("sql.deletePrefix", deletePrefix.toString());
        return map;
    }

    /**
     * 创建 update 语句
     *
     * @author arris
     * @param bean 表对应Bean
     * @return update 语句
     */
    private static String update(Bean bean) {
        StringBuffer update = new StringBuffer("update ");
        update.append(bean.getName().getOriginal());
        update.append(ScarleConstants.LINE_SEPARATOR);
        update.append("        ");
        update.append("set ");

        List<Field> list = bean.getFields();
        StringBuffer set = new StringBuffer();

        List<Field> identifys = new ArrayList<Field>();
        Field temp = null;
        for (int i = 0; i < list.size(); i++) {
            temp = list.get(i);
            if (temp.isIdentify()) {
                identifys.add(temp);
                continue;
            }

            set.append(temp.getName().getOriginal());
            set.append(" = #{");
            set.append(temp.getName().getLowerCamel());
            set.append("}");

            if (i != list.size() - 1) {
                set.append(",");
                set.append(" ");
            }

            if ((i != 0 && i % 3 == 0) || (i != 0 && i == list.size() - 1)) {
                set.append(ScarleConstants.LINE_SEPARATOR);
                set.append("        ");
            }
        }
        update.append(set);
        if (identifys.size() > 0) {
            update.append("where ");
            for (int i = 0; i < identifys.size(); i++) {
                temp = identifys.get(i);
                update.append(temp.getName().getOriginal());
                update.append(" = #{");
                update.append(temp.getName().getLowerCamel());
                update.append("}");

                if (i != identifys.size() - 1) {
                    update.append(" and ");
                }
            }
        }

        return update.toString();
    }

    /**
     * 创建 select 语句
     *
     * @author arris
     * @param bean 表对应Bean
     * @return select 语句
     */
    private static String select(Bean bean) {
        StringBuffer select = new StringBuffer("select ");

        List<Field> list = bean.getFields();
        Field logicDelete = null;
        Field temp = null;
        for (int i = 0; i < list.size(); i++) {
            temp = list.get(i);
            if ("is_delete".equals(temp.getName().getOriginal())) {
                logicDelete = temp;
            }

            select.append(bean.getName().getAlias());
            select.append(".");
            select.append(temp.getName().getOriginal());
            if (i != list.size() - 1) {
                select.append(",");
            }
            select.append(" ");
            if ((i != 0 && i % 5 == 0) || (i != 0 && i == list.size() - 1)) {
                select.append(ScarleConstants.LINE_SEPARATOR);
                select.append("        ");
            }
        }
        select.append("from ");
        select.append(bean.getName().getOriginal());
        select.append(" ");
        select.append(bean.getName().getAlias());
        select.append(ScarleConstants.LINE_SEPARATOR);
        select.append("        ");
        select.append("where 1 = 1 ");
        if (null != logicDelete) {
            select.append("and ");
            select.append(bean.getName().getAlias());
            select.append(".");
            select.append(logicDelete.getName().getOriginal());
            select.append(" = 0");
        }

        return select.toString();
    }
}
