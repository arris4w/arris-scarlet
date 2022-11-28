/*
 * 文  件  名：FileLoader.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-5-19
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arrisframework.common.StreamUtils;
import com.arrisframework.common.StringUtils;
import com.arrisframework.config.ConfigException;
import com.arrisframework.scarle.core.utils.ScarleConstants;

/**
 * 模板文件加载
 *
 * @author arris
 * @version C01 2015-5-19
 */
public class FileLoader {

    /**
     * 日志对象
     */
    private static final Logger log = LoggerFactory.getLogger(FileLoader.class);

    /**
     * 资源文件解析路径根
     */
    private static final String DELIMITER_PATH_ROOT = "/";

    /**
     * FileLoader 实例
     */
    private static FileLoader instance = new FileLoader();

    /**
     * 构造函数
     */
    private FileLoader() {

    }

    /**
     * 获取 FileLoader 实例
     *
     * @author arris
     * @return FileLoader 实例
     */
    public static FileLoader getInstance() {
        return instance;
    }

    /**
     * 加载模板文件，该配置文件必须在类的根目录下
     *
     * @author arris
     * @param filePath 待加载的属性配置文件路径
     * @return <br>
     *         加载后的属性实例<br>
     *         传入的文件地址为空时，返回null
     * @throws ConfigException 文件不存在抛出异常；<br>
     *             文件不是文本文件时抛出异常；<br>
     *             读入流时，也可能抛出异常
     */
    public String load(String filePath) {
        // 如果路径为空，返回空
        if (StringUtils.isEmpty(filePath)) {
            return null;
        }

        // 先解析绝对路径
        File file = new File(filePath);

        // 不是绝对路径
        if (!file.exists()) {
            if (!filePath.startsWith(DELIMITER_PATH_ROOT)) {
                filePath = DELIMITER_PATH_ROOT + filePath;
            }

            URL url = this.getClass().getResource(filePath);

            // 文件不存在抛出异常
            if (null == url) {
                log.warn("The Config File[filePath=" + filePath + "] does not exist.");
                return null;
            }

            // 创建文件实例
            try {
                file = new File(url.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        // 校验不能是目录，必须是文件
        if (!file.isFile()) {
            log.error("This file is not a text file, it is a directory.");
            return null;
        }

        // 从 classpath 中加载
        return load(file);
    }

    private static String load(File file) {

        StringBuilder message = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;
            while ((line = br.readLine()) != null) {
                if (file.getName().endsWith(".sql") && (line.startsWith("/*") || line.startsWith("--"))) {
                    continue;
                }
                message.append(line);
                message.append(ScarleConstants.LINE_SEPARATOR);
            }

            return message.toString();
        } catch (IOException e) {
            return null;
        } finally {
            StreamUtils.close(br);
        }
    }
}
