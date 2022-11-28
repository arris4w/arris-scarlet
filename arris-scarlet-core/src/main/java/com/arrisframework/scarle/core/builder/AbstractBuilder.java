/*
 * 文  件  名：AbstractBuilder.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2016-3-8
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.builder;

/**
 * 抽象构建
 *
 * @author arris
 * @version C01 2016-3-8
 */
public abstract class AbstractBuilder implements IBuilder {

    /**
     * 抽象构建
     */
    protected IBuilder builder;

    /**
     * 构造函数
     */
    public AbstractBuilder() {
    }

    /**
     * 构造函数
     *
     * @param builder AbstractBuilder
     */
    public AbstractBuilder(IBuilder builder) {
        this.builder = builder;
    }

    /**
     * 设置builder
     *
     * @param builder 要设置的builder。
     */
    public void setBuilder(AbstractBuilder builder) {
        this.builder = builder;
    }

}
