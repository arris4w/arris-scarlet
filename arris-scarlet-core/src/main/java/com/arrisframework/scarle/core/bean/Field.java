/*
 * 文  件  名：Filed.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-5-19
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.bean;

/**
 * Bean 属性
 * 
 * @author arris
 * @version C01 2015-5-19
 */
public class Field {
    /**
     * 名称
     */
    private Name name;

    /**
     * 类型
     */
    private String type;

    /**
     * 注释
     */
    private String comment;

    /**
     * 是否标识
     */
    private boolean identify;

    /**
     * 构造函数
     * 
     * @param name 名称
     * @param type 类型
     * @param comment 注释
     * @param identify 是否为标识
     */
    public Field(Name name, String type, String comment, boolean identify) {
        this.name = name;
        this.type = type;
        this.comment = comment;
        this.identify = identify;
    }

    /**
     * 取得name
     * 
     * @return 返回name。
     */
    public Name getName() {
        return name;
    }

    /**
     * 设置name
     * 
     * @param name 要设置的name。
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * 取得type
     * 
     * @return 返回type。
     */
    public String getType() {
        return type;
    }

    /**
     * 设置type
     * 
     * @param type 要设置的type。
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 取得comment
     * 
     * @return 返回comment。
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置comment
     * 
     * @param comment 要设置的comment。
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 取得identify
     * 
     * @return 返回identify。
     */
    public boolean isIdentify() {
        return identify;
    }

    /**
     * 设置identify
     * 
     * @param identify 要设置的identify。
     */
    public void setIdentify(boolean identify) {
        this.identify = identify;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return name + " " + type + " " + comment + " " + identify;
    }
}
