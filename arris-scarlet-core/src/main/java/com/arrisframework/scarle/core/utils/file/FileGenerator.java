/*
 * 文  件  名：FileGenerator.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-5-21
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.utils.file;

import java.io.File;
import java.io.FileOutputStream;

import com.arrisframework.common.StreamUtils;
import com.arrisframework.common.StringUtils;
import com.arrisframework.scarle.core.utils.ScarleCache;

/**
 * 文件生成
 *
 * @author arris
 * @version C01 2015-5-21
 */
public class FileGenerator {

    /**
     * 创建文件目录
     *
     * @author arris
     * @param path 待创建路径
     */
    public static void mkdir(String path) {
        File fd = null;
        try {
            fd = new File(path);
            if (!fd.exists()) {
                fd.mkdirs();
            }
        } catch (Exception e) {
        } finally {
            fd = null;
        }
    }

    public static void save(String content, String packageName, String fileName) {
        if (StringUtils.isEmpty(content) || StringUtils.isEmpty(fileName)) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        String[] names = packageName.split("\\.");
        for (String name : names) {
            sb.append(name);
            sb.append(File.separator);
        }

        String path = ScarleCache.get("store.path") + sb.toString();
        FileOutputStream fos = null;
        try {
            File outDir = new File(path);
            if (!outDir.exists()) {
                outDir.mkdirs();
            }

            File file = new File(path + fileName);
            fos = new FileOutputStream(file);

            fos.write(content.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StreamUtils.close(fos);
        }
    }
}
