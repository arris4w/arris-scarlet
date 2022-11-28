/*
 * 文  件  名：Main.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2016-2-2
 * 修改内容：新增
 */
package com.arrisframework.scarle.core;

import com.arrisframework.common.StringUtils;
import com.arrisframework.config.ConfigHelper;
import com.arrisframework.scarle.core.converter.INameConverter;
import com.arrisframework.scarle.core.utils.ReflectCache;
import com.arrisframework.scarle.core.utils.ScarleCache;
import com.arrisframework.scarle.core.utils.ScarleTypeCache;
import com.arrisframework.scarle.core.utils.exception.ScarleException;
import com.arrisframework.scarle.core.utils.file.FileLoader;

/**
 * Main
 *
 * @author arris
 * @version C01 2016-2-2
 */
public class Main {

    /**
     * 主入口
     *
     * @author arris
     * @param args args
     */
    public static void main(String[] args) {
        new ConfigHelper().init();
        String converterKey = ScarleCache.get("name.converter");
        if (StringUtils.isEmpty(converterKey)) {
            throw new ScarleException("The target name converter type [" + converterKey + "] is null.");
        }

        try {
            INameConverter converter = (INameConverter) Class.forName(converterKey).newInstance();
            ReflectCache.set(converterKey, converter);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new ScarleException("The target name converter type [" + converterKey + "] is invalid.");
        }

        String type = ScarleCache.get("target.type");
        if (StringUtils.isEmpty(converterKey)) {
            throw new ScarleException("The target type  [" + type + "] is null.");
        }

        AbstractEngine engine = null;
        if (ScarleTypeCache.MYSQL.equals(type)) {
            engine = new MySQLEngine();
        } else if (ScarleTypeCache.ORACLE.equals(type)) {
            engine = new OracleEngine();
        }

        if (null == engine) {
            throw new ScarleException("The target type  [" + type + "] is invalid.");
        }

        String sqlPath = ScarleCache.get("target.sql.path");
        if (StringUtils.isEmpty(sqlPath)) {
            throw new ScarleException("The target sql file path [" + sqlPath + "] is null.");
        }

        FileLoader loader = FileLoader.getInstance();
        String content = loader.load(sqlPath);
        if (StringUtils.isEmpty(content)) {
            throw new ScarleException("The target sql file path [" + sqlPath + "] is invalid.");
        }
        engine.service(content);
    }
}
