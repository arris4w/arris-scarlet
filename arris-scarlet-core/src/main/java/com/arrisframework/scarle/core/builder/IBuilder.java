/*
 * 文  件  名：IBuilder.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2016-3-8
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.builder;

import java.util.List;

import com.arrisframework.scarle.core.bean.Bean;

/**
 * 构建接口
 *
 * @author arris
 * @version C01 2016-3-8
 */
public interface IBuilder {

    /**
     * 构建文件
     *
     * @author arris
     * @param beans 表对应Bean数据
     */
    void process(List<Bean> beans);

}
